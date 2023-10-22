package com.javajober.spaceWall.service;

import com.fasterxml.jackson.databind.JsonNode;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import com.javajober.core.util.response.CommonResponse;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.dto.response.BlockResponse;
import com.javajober.spaceWall.dto.response.DuplicateURLResponse;
import com.javajober.spaceWall.dto.response.SpaceWallResponse;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyFactory;
import com.javajober.spaceWall.strategy.FixBlockStrategy;
import com.javajober.spaceWall.strategy.MoveBlockStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SpaceWallFindService {

    private static final String BLOCK_UUID_KEY = "block_uuid";
    private static final String BLOCK_TYPE_KEY = "block_type";
    private static final Long INITIAL_POSITION = 1L;

    private final SpaceWallRepository spaceWallRepository;
    private final BlockStrategyFactory blockStrategyFactory;
    private final BlockJsonProcessor jsonProcessor;

    public SpaceWallFindService(final SpaceWallRepository spaceWallRepository,
                                final BlockStrategyFactory blockStrategyFactory, final BlockJsonProcessor jsonProcessor) {

        this.spaceWallRepository = spaceWallRepository;
        this.blockStrategyFactory = blockStrategyFactory;
        this.jsonProcessor = jsonProcessor;
    }

    public DuplicateURLResponse hasDuplicateShareURL(final String shareURL) {
        boolean hasDuplicateURL = spaceWallRepository.existsByShareURL(shareURL);
        return new DuplicateURLResponse(hasDuplicateURL);
    }

    @Transactional
    public SpaceWallResponse findByShareURL(final String shareURL) {

        SpaceWall spaceWall = spaceWallRepository.getByShareURL(shareURL);
        Long memberId = spaceWall.getMember().getId();
        Long spaceId = spaceWall.getAddSpace().getId();
        Long spaceWallId = spaceWall.getId();

        return find(memberId, spaceId, spaceWallId, FlagType.SAVED);
    }

    @Transactional
    public SpaceWallResponse find(final Long memberId, final Long spaceId, final Long spaceWallId, final FlagType flag) {
        SpaceWall spaceWall = spaceWallRepository.findSpaceWall(spaceWallId, spaceId, memberId, flag);
        Map<Long, List<JsonNode>> groupedBlockByPosition = jsonProcessor.toJsonNode(spaceWall.getBlocks());

        CommonResponse wallInfoBlockResponse = createWallInfoBlock(groupedBlockByPosition);
        CommonResponse styleSettingResponse = createStyleSettingBlock(groupedBlockByPosition);
        List<BlockResponse<CommonResponse>> blocks = createBlockResponses(groupedBlockByPosition);

        String category = spaceWall.getSpaceWallCategoryType().getEngTitle();
        String shareURL = spaceWall.getShareURL();

        return new SpaceWallResponse(category, memberId, spaceId, shareURL, wallInfoBlockResponse, blocks, styleSettingResponse);
    }

    private CommonResponse createWallInfoBlock(Map<Long, List<JsonNode>> groupedBlockByPosition) {
        List<JsonNode> wallInfoBlocks = groupedBlockByPosition.get(INITIAL_POSITION);

        if (wallInfoBlocks == null || wallInfoBlocks.isEmpty()) {
            throw new ApplicationException(ApiStatus.FAIL, "wallInfoBlock 조회를 실패했습니다.");
        }

        FixBlockStrategy blockStrategy = blockStrategyFactory.findFixBlockStrategy(getStrategyName(wallInfoBlocks));
        return blockStrategy.createFixBlockDTO(wallInfoBlocks);
    }

    private CommonResponse createStyleSettingBlock(Map<Long, List<JsonNode>> groupedBlockByPosition) {
        Long endPosition = groupedBlockByPosition.keySet().stream()
                .max(Long::compareTo)
                .orElseThrow(() -> new ApplicationException(ApiStatus.EXCEPTION, "end position이 없습니다."));

        List<JsonNode> styleSettingBlocks = groupedBlockByPosition.get(endPosition);
        String blockTypeString = styleSettingBlocks.get(0).path(BLOCK_TYPE_KEY).asText();

        if (blockTypeString.equals(BlockType.STYLE_SETTING.getEngTitle())) {
            FixBlockStrategy blockStrategy = blockStrategyFactory.findFixBlockStrategy(getStrategyName(styleSettingBlocks));
            return blockStrategy.createFixBlockDTO(styleSettingBlocks);
        }
        return null;
    }

    private List<BlockResponse<CommonResponse>> createBlockResponses(Map<Long, List<JsonNode>> groupedBlockByPosition) {
        List<BlockResponse<CommonResponse>> blocks = new ArrayList<>();

        for (Map.Entry<Long, List<JsonNode>> entry : groupedBlockByPosition.entrySet()) {
            Long currentPosition = entry.getKey();
            List<JsonNode> blocksWithSamePosition = entry.getValue();
            String strategyName = getStrategyName(blocksWithSamePosition);

            if (currentPosition.equals(INITIAL_POSITION) || strategyName.equals(BlockType.STYLE_SETTING.getStrategyName())) {
                continue;
            }
            MoveBlockStrategy blockStrategy = blockStrategyFactory.findMoveBlockStrategy(strategyName);
            List<CommonResponse> subData = blockStrategy.createMoveBlockDTO(blocksWithSamePosition);

            String blockType = blocksWithSamePosition.get(0).path(BLOCK_TYPE_KEY).asText();
            String blockUUID = blocksWithSamePosition.get(0).path(BLOCK_UUID_KEY).asText();
            BlockResponse<CommonResponse> blockResponse = new BlockResponse<>(blockUUID, blockType, subData);
            blocks.add(blockResponse);
        }
        return blocks;
    }

    private static String getStrategyName(List<JsonNode> blocksWithSamePosition) {
        String blockTypeString = blocksWithSamePosition.get(0).path(BLOCK_TYPE_KEY).asText();
        return BlockType.findBlockTypeByString(blockTypeString).getStrategyName();
    }

}
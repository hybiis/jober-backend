package com.javajober.spaceWall.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajober.backgroundSetting.dto.response.BackgroundSettingResponse;
import com.javajober.blockSetting.dto.response.BlockSettingResponse;
import com.javajober.core.util.CommonResponse;
import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.fileBlock.domain.FileBlock;
import com.javajober.fileBlock.dto.response.FileBlockResponse;
import com.javajober.fileBlock.repository.FileBlockRepository;
import com.javajober.freeBlock.domain.FreeBlock;
import com.javajober.freeBlock.dto.response.FreeBlockResponse;
import com.javajober.freeBlock.repository.FreeBlockRepository;
import com.javajober.listBlock.domain.ListBlock;
import com.javajober.listBlock.dto.response.ListBlockResponse;
import com.javajober.listBlock.repository.ListBlockRepository;
import com.javajober.snsBlock.domain.SNSBlock;
import com.javajober.snsBlock.dto.response.SNSBlockResponse;
import com.javajober.snsBlock.repository.SNSBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWall.dto.response.BlockResponse;
import com.javajober.spaceWall.dto.response.DuplicateURLResponse;
import com.javajober.spaceWall.dto.response.SpaceWallResponse;
import com.javajober.spaceWall.repository.SpaceWallRepository;
import com.javajober.styleSetting.domain.StyleSetting;
import com.javajober.styleSetting.dto.response.StyleSettingResponse;
import com.javajober.styleSetting.repository.StyleSettingRepository;
import com.javajober.templateBlock.domain.TemplateBlock;
import com.javajober.templateBlock.dto.response.TemplateBlockResponse;
import com.javajober.templateBlock.repository.TemplateBlockRepository;
import com.javajober.themeSetting.dto.response.ThemeSettingResponse;
import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.wallInfoBlock.dto.response.WallInfoBlockResponse;
import com.javajober.wallInfoBlock.repository.WallInfoBlockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SpaceWallFindService {

    private final SpaceWallRepository spaceWallRepository;
    private final SNSBlockRepository snsBlockRepository;
    private final FreeBlockRepository freeBlockRepository;
    private final TemplateBlockRepository templateBlockRepository;
    private final WallInfoBlockRepository wallInfoBlockRepository;
    private final FileBlockRepository fileBlockRepository;
    private final ListBlockRepository listBlockRepository;
    private final StyleSettingRepository styleSettingRepository;

    public SpaceWallFindService(
            final SpaceWallRepository spaceWallRepository, final SNSBlockRepository snsBlockRepository,
            final FreeBlockRepository freeBlockRepository, final TemplateBlockRepository templateBlockRepository,
            final WallInfoBlockRepository wallInfoBlockRepository, final FileBlockRepository fileBlockRepository,
            final ListBlockRepository listBlockRepository, final StyleSettingRepository styleSettingRepository) {

        this.spaceWallRepository = spaceWallRepository;
        this.snsBlockRepository = snsBlockRepository;
        this.freeBlockRepository = freeBlockRepository;
        this.templateBlockRepository = templateBlockRepository;
        this.wallInfoBlockRepository = wallInfoBlockRepository;
        this.fileBlockRepository = fileBlockRepository;
        this.listBlockRepository = listBlockRepository;
        this.styleSettingRepository = styleSettingRepository;
    }

    public DuplicateURLResponse hasDuplicateShareURL(final String shareURL) {
        boolean hasDuplicateURL = spaceWallRepository.existsByShareURL(shareURL);
        return new DuplicateURLResponse(hasDuplicateURL);
    }

    @Transactional
    public SpaceWallResponse findByShareURL(final String shareURL){

        SpaceWall spaceWall = spaceWallRepository.getByShareURL(shareURL);
        Long memberId = spaceWall.getMember().getId();
        Long spaceId = spaceWall.getAddSpace().getId();
        Long spaceWallId = spaceWall.getId();

        return find(memberId, spaceId, spaceWallId, FlagType.SAVED);
    }

    @Transactional
    public SpaceWallResponse find(final Long memberId, final Long spaceId, final Long spaceWallId, final FlagType flag) {

        SpaceWall spaceWall = spaceWallRepository.findSpaceWall(spaceWallId, spaceId, memberId, flag);
        String blocksPS = spaceWall.getBlocks();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(blocksPS);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(ApiStatus.EXCEPTION, "Json을 변환 중 오류가 발생했습니다.");
        }
        List<BlockResponse<CommonResponse>> blocks = new ArrayList<>();
        WallInfoBlockResponse wallInfoBlockResponse = new WallInfoBlockResponse();
        StyleSettingResponse styleSettingResponse = new StyleSettingResponse();

        Map<Long, List<JsonNode>> groupedNodesByPosition = StreamSupport.stream(rootNode.spliterator(), false)
                .sorted(Comparator.comparingInt(a -> a.get("position").asInt()))
                .collect(Collectors.groupingBy(node -> (long) node.get("position").asInt()));

        Long maxPosition = groupedNodesByPosition.keySet().stream()
                .max(Long::compareTo)
                .orElse(null);

        for (Map.Entry<Long, List<JsonNode>> entry : groupedNodesByPosition.entrySet()) {
            Long currentPosition = entry.getKey();
            if (currentPosition.equals(1L)) {
                wallInfoBlockResponse = createWallInfoBlockDTO(entry);
                continue;
            }
            if (currentPosition.equals(maxPosition)) {
                styleSettingResponse = createStyleSettingDTO(entry);
                continue;
            }

            String blockUUID = "";
            String blockTypeString = "";
            List<JsonNode> nodesWithSamePosition = entry.getValue();
            List<CommonResponse> subData = new ArrayList<>();

            for (JsonNode node : nodesWithSamePosition) {

                Long blockId = node.get("blockId").asLong();
                blockUUID = node.get("blockUUID").asText();

                blockTypeString = node.get("blockType").asText();
                BlockType blockType = BlockType.findBlockTypeByString(blockTypeString);

                subData.add(createBlockDTO(blockType, blockId));
            }
            BlockResponse<CommonResponse> blockResponse = BlockResponse.from(blockUUID, blockTypeString, subData);
            blocks.add(blockResponse);
        }
        String category = spaceWall.getSpaceWallCategoryType().getEngTitle();
        String shareURL = spaceWall.getShareURL();

        return new SpaceWallResponse(category, memberId, spaceId, shareURL, wallInfoBlockResponse, blocks, styleSettingResponse);
    }

    private CommonResponse createBlockDTO(final BlockType blockType, final Long blockId) {

        switch (blockType) {
            case FREE_BLOCK:
                FreeBlock freeBlock = freeBlockRepository.findFreeBlock(blockId);
                return FreeBlockResponse.from(freeBlock);
            case SNS_BLOCK:
                SNSBlock snsBlock = snsBlockRepository.findSNSBlock(blockId);
                return SNSBlockResponse.from(snsBlock);
            case FILE_BLOCK:
                FileBlock fileBlock = fileBlockRepository.findFileBlock(blockId);
                return FileBlockResponse.from(fileBlock);
            case LIST_BLOCK:
                ListBlock listBlock = listBlockRepository.findListBlock(blockId);
                return ListBlockResponse.from(listBlock);
            case TEMPLATE_BLOCK:
                TemplateBlock templateBlock = templateBlockRepository.findTemplateBlock(blockId);
                return TemplateBlockResponse.of(templateBlock, Collections.emptyList(), Collections.emptyList());
        }
        return null;
    }

    private WallInfoBlockResponse createWallInfoBlockDTO(final Map.Entry<Long, List<JsonNode>> entry) {

        Long blockId = entry.getValue().get(0).get("blockId").asLong();
        WallInfoBlock wallInfoBlock = wallInfoBlockRepository.findWallInfoBlock(blockId);

        return WallInfoBlockResponse.from(wallInfoBlock);
    }

    private StyleSettingResponse createStyleSettingDTO(final Map.Entry<Long, List<JsonNode>> entry) {

        Long styleSettingBlockId = entry.getValue().get(0).get("blockId").asLong();
        StyleSetting styleSetting = styleSettingRepository.findStyleBlock(styleSettingBlockId);

        BackgroundSettingResponse backgroundSettingResponse = BackgroundSettingResponse.from(styleSetting.getBackgroundSetting());
        BlockSettingResponse blockSettingResponse = BlockSettingResponse.from(styleSetting.getBlockSetting());
        ThemeSettingResponse themeSettingResponse = ThemeSettingResponse.from(styleSetting.getThemeSetting());

        return new StyleSettingResponse(styleSettingBlockId, backgroundSettingResponse, blockSettingResponse, themeSettingResponse);

    }
}
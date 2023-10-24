package com.javajober.spaceWall.strategy.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.blocks.wallInfoBlock.dto.response.WallInfoBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.blocks.wallInfoBlock.dto.request.WallInfoBlockStringSaveRequest;
import com.javajober.blocks.wallInfoBlock.repository.WallInfoBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.dto.request.DataStringSaveRequest;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.FixBlockStrategy;

import java.util.List;


@Component
public class WallInfoBlockStrategy implements FixBlockStrategy {
	private static final String WALL_INFO_BLOCK  = BlockType.WALL_INFO_BLOCK.getEngTitle();
	private final BlockJsonProcessor blockJsonProcessor;
	private final WallInfoBlockRepository wallInfoBlockRepository;

	public WallInfoBlockStrategy(BlockJsonProcessor blockJsonProcessor, WallInfoBlockRepository wallInfoBlockRepository) {
		this.blockJsonProcessor = blockJsonProcessor;
		this.wallInfoBlockRepository = wallInfoBlockRepository;
	}

	@Override
	public void saveBlocks(final DataStringSaveRequest data, ArrayNode blockInfoArray, Long position) {
		WallInfoBlockStringSaveRequest request = data.getWallInfoBlock();
		Long wallInfoBlockId = saveWallInfoBlock(request);
		blockJsonProcessor.addBlockInfoToArray(blockInfoArray, position, WALL_INFO_BLOCK, wallInfoBlockId,"");
	}

	private Long saveWallInfoBlock(WallInfoBlockStringSaveRequest request) {

		WallInfoBlock wallInfoBlock = WallInfoBlockStringSaveRequest.toEntity(request);
		return wallInfoBlockRepository.save(wallInfoBlock).getId();
	}

	@Override
	public CommonResponse createFixBlockDTO(List<JsonNode> fixBlocks) {
		long blockId = fixBlocks.get(0).path("block_id").asLong();
		WallInfoBlock wallInfoBlock = wallInfoBlockRepository.findWallInfoBlock(blockId);

		return WallInfoBlockResponse.from(wallInfoBlock);
	}

	@Override
	public String getStrategyName() {
		return BlockStrategyName.WallInfoBlockStrategy.name();
	}
}

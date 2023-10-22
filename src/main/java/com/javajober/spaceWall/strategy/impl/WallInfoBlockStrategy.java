package com.javajober.spaceWall.strategy.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.javajober.blocks.wallInfoBlock.dto.response.WallInfoBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.wallInfoBlock.domain.WallInfoBlock;
import com.javajober.blocks.wallInfoBlock.dto.request.WallInfoBlockStringSaveRequest;
import com.javajober.blocks.wallInfoBlock.repository.WallInfoBlockRepository;
import com.javajober.spaceWall.dto.request.DataStringSaveRequest;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.FixBlockStrategy;

import java.util.List;

@Component
public class WallInfoBlockStrategy implements FixBlockStrategy {

	private final WallInfoBlockRepository wallInfoBlockRepository;

	public WallInfoBlockStrategy(WallInfoBlockRepository wallInfoBlockRepository) {
		this.wallInfoBlockRepository = wallInfoBlockRepository;
	}

	@Override
	public Long saveBlocks(DataStringSaveRequest data) {
		WallInfoBlockStringSaveRequest request = data.getWallInfoBlock();
		return saveWallInfoBlock(request);
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

	private Long saveWallInfoBlock(WallInfoBlockStringSaveRequest request) {
		WallInfoBlock wallInfoBlock = WallInfoBlockStringSaveRequest.toEntity(request);
		return wallInfoBlockRepository.save(wallInfoBlock).getId();
	}
}

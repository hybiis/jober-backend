package com.javajober.spaceWall.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.blocks.snsBlock.dto.response.SNSBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.snsBlock.domain.SNSBlock;
import com.javajober.blocks.snsBlock.dto.request.SNSBlockSaveRequest;
import com.javajober.blocks.snsBlock.repository.SNSBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.MoveBlockStrategy;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SNSBlockStrategy implements MoveBlockStrategy {

	private static final String SNS_BLOCK = BlockType.SNS_BLOCK.getEngTitle();
	private final BlockJsonProcessor blockJsonProcessor;
	private final SNSBlockRepository snsBlockRepository;

	public SNSBlockStrategy(final BlockJsonProcessor blockJsonProcessor, final SNSBlockRepository snsBlockRepository) {
		this.blockJsonProcessor = blockJsonProcessor;
		this.snsBlockRepository = snsBlockRepository;
	}

	@Override
	public void saveBlocks(final List<?> subData, final ArrayNode blockInfoArray, final Long position) {

		subData.forEach(block -> {
			SNSBlockSaveRequest request = blockJsonProcessor.convertValue(block, SNSBlockSaveRequest.class);
			SNSBlock snsBlock = saveSNSBlock(request);
			blockJsonProcessor.addBlockInfoToArray(blockInfoArray, position, SNS_BLOCK, snsBlock.getId(), snsBlock.getSnsUUID());
		});
	}

	private SNSBlock saveSNSBlock(SNSBlockSaveRequest request) {
		SNSBlock snsBlock = SNSBlockSaveRequest.toEntity(request);
		snsBlockRepository.save(snsBlock);
		return snsBlock;
	}

	@Override
	public List<CommonResponse> createMoveBlockDTO(List<JsonNode> blocksWithSamePosition) {
		List<CommonResponse> subData = new ArrayList<>();
		for (JsonNode block : blocksWithSamePosition) {
			long blockId = block.path("block_id").asLong();
			SNSBlock snsBlock = snsBlockRepository.findSNSBlock(blockId);
			subData.add(SNSBlockResponse.from(snsBlock));
		}
		return subData;
	}

	@Override
	public String getStrategyName() {
		return BlockStrategyName.SNSBlockStrategy.name();
	}
}

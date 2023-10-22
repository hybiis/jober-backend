package com.javajober.spaceWall.strategy.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.javajober.blocks.templateBlock.dto.response.TemplateBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.templateBlock.domain.TemplateBlock;
import com.javajober.blocks.templateBlock.dto.request.TemplateBlockSaveRequest;
import com.javajober.blocks.templateBlock.repository.TemplateBlockRepository;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.MoveBlockStrategy;


@Component
public class TemplateBlockStrategy implements MoveBlockStrategy {

	private final BlockJsonProcessor blockJsonProcessor;
	private final TemplateBlockRepository templateBlockRepository;

	public TemplateBlockStrategy(final BlockJsonProcessor blockJsonProcessor, final TemplateBlockRepository templateBlockRepository) {
		this.blockJsonProcessor = blockJsonProcessor;
		this.templateBlockRepository = templateBlockRepository;
	}

	@Override
	public List<Long> saveBlocks(final List<Object> subData) {
		List<Long> templateBlockIds = new ArrayList<>();

		subData.forEach(block -> {
			TemplateBlockSaveRequest request = blockJsonProcessor.convertValue(block, TemplateBlockSaveRequest.class);
			TemplateBlock templateBlock = TemplateBlockSaveRequest.toEntity(request);
			templateBlockIds.add(templateBlockRepository.save(templateBlock).getId());
		});

		return templateBlockIds;
	}

	@Override
	public List<CommonResponse> createMoveBlockDTO(List<JsonNode> blocksWithSamePosition) {
		List<CommonResponse> subData = new ArrayList<>();
		for (JsonNode block : blocksWithSamePosition) {
			long blockId = block.path("block_id").asLong();
			TemplateBlock templateBlock = templateBlockRepository.findTemplateBlock(blockId);
			subData.add(TemplateBlockResponse.of(templateBlock, Collections.emptyList(), Collections.emptyList()));
		}
		return subData;
	}

	@Override
	public String getStrategyName() {
		return BlockStrategyName.TemplateBlockStrategy.name();
	}
}

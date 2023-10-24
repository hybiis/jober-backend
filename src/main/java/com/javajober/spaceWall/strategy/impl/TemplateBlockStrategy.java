package com.javajober.spaceWall.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.blocks.templateBlock.dto.response.TemplateBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.templateBlock.domain.TemplateBlock;
import com.javajober.blocks.templateBlock.dto.request.TemplateBlockSaveRequest;
import com.javajober.blocks.templateBlock.repository.TemplateBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.MoveBlockStrategy;

@Component
public class TemplateBlockStrategy implements MoveBlockStrategy {

	private static final String TEMPLATE_BLOCK = BlockType.TEMPLATE_BLOCK.getEngTitle();
	private final BlockJsonProcessor blockJsonProcessor;
	private final TemplateBlockRepository templateBlockRepository;

	public TemplateBlockStrategy(final BlockJsonProcessor blockJsonProcessor, final TemplateBlockRepository templateBlockRepository) {
		this.blockJsonProcessor = blockJsonProcessor;
		this.templateBlockRepository = templateBlockRepository;
	}

	@Override
	public void saveBlocks(final List<?> subData, final ArrayNode blockInfoArray, final Long position) {

		subData.forEach(block -> {
			TemplateBlockSaveRequest request = blockJsonProcessor.convertValue(block, TemplateBlockSaveRequest.class);
			TemplateBlock templateBlock = saveTemplateBlock(request);
			blockJsonProcessor.addBlockInfoToArray(blockInfoArray, position, TEMPLATE_BLOCK, templateBlock.getId(), templateBlock.getTemplateUUID());
		});
	}

	private TemplateBlock saveTemplateBlock(TemplateBlockSaveRequest request) {
		TemplateBlock templateBlock = TemplateBlockSaveRequest.toEntity(request);
		templateBlockRepository.save(templateBlock);
		return templateBlock;
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

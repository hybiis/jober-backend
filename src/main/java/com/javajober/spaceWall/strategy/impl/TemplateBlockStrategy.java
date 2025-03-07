package com.javajober.spaceWall.strategy.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.blocks.templateBlock.dto.request.TemplateBlockUpdateRequest;
import com.javajober.blocks.templateBlock.dto.response.TemplateBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.templateBlock.domain.TemplateBlock;
import com.javajober.blocks.templateBlock.dto.request.TemplateBlockSaveRequest;
import com.javajober.blocks.templateBlock.repository.TemplateBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.dto.request.BlockSaveRequest;
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
	public void saveStringBlocks(final BlockSaveRequest<?> block, final ArrayNode blockInfoArray, final Long position) {
		List<TemplateBlockSaveRequest> templateBlockRequests = convertSubDataToTemplateBlockSaveRequests(block.getSubData());

		List<TemplateBlock> templateBlocks = convertToTemplateBlocks(templateBlockRequests);

		List<TemplateBlock> savedTemplateBlocks = saveAllTemplateBlock(templateBlocks);

		addToTemplateBlockInfoArray(savedTemplateBlocks, blockInfoArray, position, block.getBlockUUID());
	}

	@Override
	public void saveBlocks(final BlockSaveRequest<?> block, final ArrayNode blockInfoArray, final Long position) {
		List<TemplateBlockSaveRequest> templateBlockRequests = convertSubDataToTemplateBlockSaveRequests(block.getSubData());

		List<TemplateBlock> templateBlocks = convertToTemplateBlocks(templateBlockRequests);

		List<TemplateBlock> savedTemplateBlocks = saveAllTemplateBlock(templateBlocks);

		addToTemplateBlockInfoArray(savedTemplateBlocks, blockInfoArray, position, block.getBlockUUID());
	}

	private List<TemplateBlockSaveRequest> convertSubDataToTemplateBlockSaveRequests(final List<?> subData) {
		List<TemplateBlockSaveRequest> templateBlockRequests = new ArrayList<>();

		subData.forEach(block -> {
			TemplateBlockSaveRequest request = blockJsonProcessor.convertValue(block, TemplateBlockSaveRequest.class);
			templateBlockRequests.add(request);
		});
		return templateBlockRequests;
	}

	private List<TemplateBlock> convertToTemplateBlocks(final List<TemplateBlockSaveRequest> templateBlockSaveRequests) {
		return templateBlockSaveRequests.stream()
			.map(TemplateBlockSaveRequest::toEntity)
			.collect(Collectors.toList());
	}

	private List<TemplateBlock> saveAllTemplateBlock(final List<TemplateBlock> templateBlocks) {
		return templateBlockRepository.saveAll(templateBlocks);
	}

	private void addToTemplateBlockInfoArray (final List<TemplateBlock> savedTemplateBlocks, final ArrayNode blockInfoArray, final Long position, String templateBlockUUID) {
		savedTemplateBlocks.forEach(savedTemplateBlock ->
			blockJsonProcessor.addBlockInfoToArray(blockInfoArray, position, BlockType.TEMPLATE_BLOCK, savedTemplateBlock.getId(), templateBlockUUID)
		);
	}

	@Override
	public List<CommonResponse> createMoveBlockDTO(final List<JsonNode> blocksWithSamePosition) {
		List<CommonResponse> subData = new ArrayList<>();
		for (JsonNode block : blocksWithSamePosition) {
			long blockId = block.path("block_id").asLong();
			TemplateBlock templateBlock = templateBlockRepository.findTemplateBlock(blockId);
			subData.add(TemplateBlockResponse.of(templateBlock, Collections.emptyList(), Collections.emptyList()));
		}
		return subData;
	}

	@Override
	public Set<Long> updateBlocks(final BlockSaveRequest<?> blocks, final ArrayNode blockInfoArray, final Long position) {

		List<TemplateBlock> templateBlocks = new ArrayList<>();

		blocks.getSubData().forEach(block -> {
			TemplateBlockUpdateRequest request = blockJsonProcessor.convertValue(block, TemplateBlockUpdateRequest.class);
			TemplateBlock templateBlock = saveOrUpdateTemplateBlock(request);
			templateBlocks.add(templateBlock);
		});

		List<TemplateBlock> updateTemplateBlocks = templateBlockRepository.saveAll(templateBlocks);

		return updateTemplateBlocks.stream().map(TemplateBlock::getId).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	private TemplateBlock saveOrUpdateTemplateBlock(final TemplateBlockUpdateRequest request) {

		if (request.getTemplateBlockId() == null) {
			return TemplateBlockUpdateRequest.toEntity(request);
		}

		TemplateBlock templateBlock = templateBlockRepository.findTemplateBlock(request.getTemplateBlockId());
		templateBlock.update(request);

		return templateBlock;
	}

	@Override
	public void deleteAllById(final Set<Long> blockIds) {
		templateBlockRepository.deleteAllById(blockIds);
	}

	@Override
	public String getStrategyName() {
		return BlockStrategyName.TemplateBlockStrategy.name();
	}
}

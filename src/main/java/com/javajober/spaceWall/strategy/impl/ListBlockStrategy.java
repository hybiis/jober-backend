package com.javajober.spaceWall.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.blocks.listBlock.dto.response.ListBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.listBlock.domain.ListBlock;
import com.javajober.blocks.listBlock.dto.request.ListBlockSaveRequest;
import com.javajober.blocks.listBlock.repository.ListBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.MoveBlockStrategy;

@Component
public class ListBlockStrategy implements MoveBlockStrategy {

	private static final String LIST_BLOCK = BlockType.LIST_BLOCK.getEngTitle();
	private final BlockJsonProcessor blockJsonProcessor;
	private final ListBlockRepository listBlockRepository;

	public ListBlockStrategy(final BlockJsonProcessor blockJsonProcessor, final ListBlockRepository listBlockRepository) {
		this.blockJsonProcessor = blockJsonProcessor;
		this.listBlockRepository = listBlockRepository;
	}

	@Override
	public void saveBlocks(final List<?> subData, final ArrayNode blockInfoArray, final Long position) {

		subData.forEach(block -> {
			ListBlockSaveRequest request = blockJsonProcessor.convertValue(block, ListBlockSaveRequest.class);
			ListBlock listBlock = saveListBlock(request);
			blockJsonProcessor.addBlockInfoToArray(blockInfoArray, position, LIST_BLOCK, listBlock.getId(), listBlock.getListUUID());
		});
	}

	private ListBlock saveListBlock(ListBlockSaveRequest request) {
		ListBlock listBlock = ListBlockSaveRequest.toEntity(request);
		return listBlockRepository.save(listBlock);
	}

	@Override
	public List<CommonResponse> createMoveBlockDTO(List<JsonNode> blocksWithSamePosition) {
		List<CommonResponse> subData = new ArrayList<>();
		for (JsonNode block : blocksWithSamePosition) {
			long blockId = block.path("block_id").asLong();
			ListBlock listBlock = listBlockRepository.findListBlock(blockId);
			subData.add(ListBlockResponse.from(listBlock));
		}
		return subData;
	}

	@Override
	public String getStrategyName() {
		return BlockStrategyName.ListBlockStrategy.name();
	}
}

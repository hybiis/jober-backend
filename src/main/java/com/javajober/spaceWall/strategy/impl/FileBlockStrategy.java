package com.javajober.spaceWall.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.blocks.fileBlock.dto.response.FileBlockResponse;
import com.javajober.core.util.response.CommonResponse;
import org.springframework.stereotype.Component;

import com.javajober.blocks.fileBlock.domain.FileBlock;
import com.javajober.blocks.fileBlock.dto.request.FileBlockStringSaveRequest;
import com.javajober.blocks.fileBlock.repository.FileBlockRepository;
import com.javajober.spaceWall.domain.BlockType;
import com.javajober.spaceWall.strategy.BlockJsonProcessor;
import com.javajober.spaceWall.strategy.BlockStrategyName;
import com.javajober.spaceWall.strategy.MoveBlockStrategy;

@Component
public class FileBlockStrategy implements MoveBlockStrategy {

	private static final String FILE_BLOCK = BlockType.FILE_BLOCK.getEngTitle();
	private final BlockJsonProcessor blockJsonProcessor;
	private final FileBlockRepository fileBlockRepository;


	public FileBlockStrategy(BlockJsonProcessor blockJsonProcessor, FileBlockRepository fileBlockRepository) {
		this.blockJsonProcessor = blockJsonProcessor;
		this.fileBlockRepository = fileBlockRepository;
	}

	@Override
	public void saveBlocks(final List<?> subData, final ArrayNode blockInfoArray, final Long position) {

		subData.forEach(block -> {
			FileBlockStringSaveRequest request = blockJsonProcessor.convertValue(block, FileBlockStringSaveRequest.class);
			Long fileBlockId = saveFreeBlock(request);
			blockJsonProcessor.addBlockInfoToArray(blockInfoArray, fileBlockId, FILE_BLOCK, fileBlockId, "");
		});
	}

	private Long saveFreeBlock(FileBlockStringSaveRequest request) {
		FileBlock fileBlock = FileBlockStringSaveRequest.toEntity(request);
		return fileBlockRepository.save(fileBlock).getId();
	}


	@Override
	public List<CommonResponse> createMoveBlockDTO(List<JsonNode> blocksWithSamePosition) {
		List<CommonResponse> subData = new ArrayList<>();
		for (JsonNode block : blocksWithSamePosition) {
			long blockId = block.path("block_id").asLong();
			FileBlock fileBlock = fileBlockRepository.findFileBlock(blockId);
			subData.add(FileBlockResponse.from(fileBlock));
		}
		return subData;
	}

	@Override
	public String getStrategyName() {
		return BlockStrategyName.FileBlockStrategy.name();
	}
}

package com.javajober.spaceWall.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.javajober.spaceWall.dto.request.BlockSaveRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BlockJsonProcessor {
	private final ObjectMapper jsonMapper;

	public BlockJsonProcessor(final ObjectMapper jsonMapper) {
		this.jsonMapper = jsonMapper;
	}

	public ArrayNode createArrayNode() {
		return jsonMapper.createArrayNode();
	}

	public <T> T convertValue(final Object fromValue, final Class<T> toValueType) {
		return jsonMapper.convertValue(fromValue, toValueType);
	}

	public void addBlockInfoToArray(final ArrayNode blockInfoArray, final Long position, final Long blockId, final BlockSaveRequest block) {

		String currentBlockTypeTitle = block.getBlockType();
		String blockUUID = block.getBlockUUID();

		ObjectNode blockInfoObject = jsonMapper.createObjectNode();

		blockInfoObject.put("position", position);
		blockInfoObject.put("block_type", currentBlockTypeTitle);
		blockInfoObject.put("block_id", blockId);
		blockInfoObject.put("block_uuid", blockUUID);

		blockInfoArray.add(blockInfoObject);
	}


	public void addBlockToJsonArray(final ArrayNode blockInfoArray, final Long position, final String blockType, final Long blockId) {

		ObjectNode blockInfoObject = jsonMapper.createObjectNode();

		blockInfoObject.put("position", position);
		blockInfoObject.put("block_type", blockType);
		blockInfoObject.put("block_id", blockId);
		blockInfoObject.put("block_uuid", "");

		blockInfoArray.add(blockInfoObject);
	}

	public Map<Long, List<JsonNode>> toJsonNode(String blocks) {
		try {
			JsonNode jsonNode = jsonMapper.readTree(blocks);
			return StreamSupport.stream(jsonNode.spliterator(), false)
					.sorted(Comparator.comparingInt(a -> a.get("position").asInt()))
					.collect(Collectors.groupingBy(node -> (long) node.get("position").asInt()));
		} catch (JsonProcessingException e) {
			throw new ApplicationException(ApiStatus.FAIL, "제이슨 변환 중 실패하였습니다.");
		}
	}
}

package com.javajober.spaceWall.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.javajober.core.exception.ApiStatus;
import com.javajober.core.exception.ApplicationException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		try {
			return jsonMapper.convertValue(fromValue, toValueType);
		} catch (IllegalArgumentException e) {
			String errorMessage = String.format("'%s' 값을 '%s' 타입으로 변환하는데 실패했습니다. 오류: %s", fromValue, toValueType.getName(), e.getMessage());
			log.error(errorMessage);
			throw new ApplicationException(ApiStatus.FAIL, "데이터를 처리하는 중 문제가 발생했습니다.");
		}
	}

	public void addBlockInfoToArray(final ArrayNode blockInfoArray, final Long position, final String blockType, final Long blockId, final String blockUUID) {

		ObjectNode blockInfoObject = jsonMapper.createObjectNode();

		blockInfoObject.put("position", position);
		blockInfoObject.put("block_type", blockType);
		blockInfoObject.put("block_id", blockId);
		blockInfoObject.put("block_uuid", blockUUID);

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

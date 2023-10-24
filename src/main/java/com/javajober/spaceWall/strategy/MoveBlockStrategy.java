package com.javajober.spaceWall.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.javajober.core.util.response.CommonResponse;

import java.util.List;

public interface MoveBlockStrategy {
	void saveBlocks(final List<?> subData, final ArrayNode blockInfoArray, final Long position);

	List<CommonResponse> createMoveBlockDTO(List<JsonNode> blocksWithSamePosition);

	String getStrategyName();
}


package com.javajober.spaceWall.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.javajober.core.util.response.CommonResponse;

import java.util.List;

public interface MoveBlockStrategy {
	List<Long> saveBlocks(final List<Object> subData);

	List<CommonResponse> createMoveBlockDTO(List<JsonNode> blocksWithSamePosition);
	String getStrategyName();
}


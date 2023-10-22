package com.javajober.spaceWall.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.javajober.core.util.response.CommonResponse;
import com.javajober.spaceWall.dto.request.DataStringSaveRequest;

import java.util.List;

public interface FixBlockStrategy {
	Long saveBlocks(final DataStringSaveRequest data);

	CommonResponse createFixBlockDTO(List<JsonNode> fixBlocks);

	String getStrategyName();
}

package com.javajober.spaceWall.dto.request;

import java.util.List;

import com.javajober.core.util.response.CommonResponse;
import lombok.Getter;

@Getter
public class BlockSaveRequest<T> {

	private String blockUUID;
	private String blockType;
	private List<T> subData;

	private BlockSaveRequest() {

	}

	public BlockSaveRequest(final String blockUUID, final String blockType, final List<T> subData) {
		this.blockUUID = blockUUID;
		this.blockType = blockType;
		this.subData = subData;
	}
}
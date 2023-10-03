package com.javajober.wallInfoBlock.dto.request;

import com.javajober.wallInfoBlock.domain.WallInfoBlock;

import lombok.Getter;

@Getter
public class WallInfoBlockUpdateRequest {

	private Long wallInfoId;
	private String wallInfoTitle;
	private String wallInfoDescription;

	private WallInfoBlockUpdateRequest() {

	}

	public WallInfoBlockUpdateRequest(final String wallInfoTitle, final String wallInfoDescription) {
		this.wallInfoTitle = wallInfoTitle;
		this.wallInfoDescription = wallInfoDescription;
	}

	public static WallInfoBlock toEntity(WallInfoBlockUpdateRequest wallInfoBlock) {
		return WallInfoBlock.builder()
			.wallInfoTitle(wallInfoBlock.getWallInfoTitle())
			.wallInfoDescription(wallInfoBlock.getWallInfoDescription())
			.build();
	}
}

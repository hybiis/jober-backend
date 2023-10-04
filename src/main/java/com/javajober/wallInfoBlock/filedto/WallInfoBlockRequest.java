package com.javajober.wallInfoBlock.filedto;

import com.javajober.wallInfoBlock.domain.WallInfoBlock;

import lombok.Getter;

@Getter
public class WallInfoBlockRequest {

	private String wallInfoTitle;
	private String wallInfoDescription;

	private WallInfoBlockRequest() {

	}

	public WallInfoBlockRequest(final String wallInfoTitle, final String wallInfoDescription) {
		this.wallInfoTitle = wallInfoTitle;
		this.wallInfoDescription = wallInfoDescription;
	}

	public static WallInfoBlock toEntity(final WallInfoBlockRequest wallInfoBlock, String backgroundImgName, String wallInfoImgName) {
		return WallInfoBlock.builder()
				.wallInfoTitle(wallInfoBlock.getWallInfoTitle())
				.wallInfoDescription(wallInfoBlock.getWallInfoDescription())
				.wallInfoBackgroundImageUrl(backgroundImgName)
				.wallInfoProfileImageUrl(wallInfoImgName)
				.build();
	}
}

package com.javajober.blocks.wallInfoBlock.filedto;

import com.javajober.blocks.wallInfoBlock.domain.WallInfoBlock;

import lombok.Getter;

@Getter
public class WallInfoBlockSaveRequest {

	private String wallInfoTitle;
	private String wallInfoDescription;

	private WallInfoBlockSaveRequest() {

	}

	public WallInfoBlockSaveRequest(final String wallInfoTitle, final String wallInfoDescription) {
		this.wallInfoTitle = wallInfoTitle;
		this.wallInfoDescription = wallInfoDescription;
	}

	public static WallInfoBlock toEntity(final WallInfoBlockSaveRequest wallInfoBlock, final String backgroundImgName, final String wallInfoImgName) {
		return WallInfoBlock.builder()
				.wallInfoTitle(wallInfoBlock.getWallInfoTitle())
				.wallInfoDescription(wallInfoBlock.getWallInfoDescription())
				.wallInfoBackgroundImageUrl(backgroundImgName)
				.wallInfoProfileImageUrl(wallInfoImgName)
				.build();
	}
}
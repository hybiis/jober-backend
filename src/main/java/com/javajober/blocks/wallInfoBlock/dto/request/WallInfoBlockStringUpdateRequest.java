package com.javajober.blocks.wallInfoBlock.dto.request;

import lombok.Getter;

@Getter
public class WallInfoBlockStringUpdateRequest {

	private Long wallInfoBlockId;
	private String wallInfoTitle;
	private String wallInfoDescription;
	private String backgroundImgURL;
	private String wallInfoImgURL;

	private WallInfoBlockStringUpdateRequest() {

	}

	public WallInfoBlockStringUpdateRequest(final String wallInfoTitle, final String wallInfoDescription, final String backgroundImgURL,
		final String wallInfoImgURL) {
		this.wallInfoTitle = wallInfoTitle;
		this.wallInfoDescription = wallInfoDescription;
		this.backgroundImgURL = backgroundImgURL;
		this.wallInfoImgURL = wallInfoImgURL;
	}
}
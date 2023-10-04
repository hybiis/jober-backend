package com.javajober.wallInfoBlock.filedto;

import com.javajober.wallInfoBlock.domain.WallInfoBlock;

import lombok.Getter;

@Getter
public class WallInfoBlockUpdateRequest {

	private Long wallInfoBlockId;
	private String wallInfoTitle;
	private String wallInfoDescription;

	private WallInfoBlockUpdateRequest() {

	}

	public WallInfoBlockUpdateRequest(final String wallInfoTitle, final String wallInfoDescription) {
		this.wallInfoTitle = wallInfoTitle;
		this.wallInfoDescription = wallInfoDescription;
	}

}

package com.javajober.spaceWall.dto.request;

import java.util.List;

import com.javajober.styleSetting.dto.request.StyleSettingSaveRequest;
import com.javajober.wallInfoBlock.dto.request.WallInfoBlockRequest;

import lombok.Getter;

@Getter
public class DataUpdateRequest {
	private Long spaceWallId;
	private String shareURL;
	private WallInfoBlockRequest wallInfoBlock;
	private List<BlockRequest> blocks;
	private StyleSettingSaveRequest styleSetting;

	private DataUpdateRequest(){
	}

	public DataUpdateRequest(final Long spaceWallId, final String shareURL, final WallInfoBlockRequest wallInfoBlock, final List<BlockRequest> blocks, final StyleSettingSaveRequest styleSetting) {
		this.spaceWallId = spaceWallId;
		this.shareURL = shareURL;
		this.wallInfoBlock = wallInfoBlock;
		this.blocks = blocks;
		this.styleSetting = styleSetting;
	}
}

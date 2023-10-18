package com.javajober.spaceWall.dto.request;

import java.util.List;

import com.javajober.styleSetting.dto.request.StyleSettingStringUpdateRequest;
import com.javajober.wallInfoBlock.dto.request.WallInfoBlockStringUpdateRequest;

import lombok.Getter;

@Getter
public class DataStringUpdateRequest {

	private Long memberId;
	private Long spaceId;
	private Long spaceWallId;
	private String shareURL;
	private WallInfoBlockStringUpdateRequest wallInfoBlock;
	private List<BlockSaveRequest> blocks;
	private StyleSettingStringUpdateRequest styleSetting;

	private DataStringUpdateRequest(){
	}

	public DataStringUpdateRequest(final Long spaceWallId, final Long spaceId, final Long memberId, final String shareURL, final WallInfoBlockStringUpdateRequest wallInfoBlock, final List<BlockSaveRequest> blocks, final StyleSettingStringUpdateRequest styleSetting) {
		this.spaceWallId = spaceWallId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.shareURL = shareURL;
		this.wallInfoBlock = wallInfoBlock;
		this.blocks = blocks;
		this.styleSetting = styleSetting;
	}
}
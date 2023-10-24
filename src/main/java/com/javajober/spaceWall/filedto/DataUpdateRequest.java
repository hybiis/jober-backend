package com.javajober.spaceWall.filedto;

import java.util.List;

import com.javajober.spaceWall.dto.request.BlockSaveRequest;
import com.javajober.blocks.styleSetting.filedto.StyleSettingUpdateRequest;
import com.javajober.blocks.wallInfoBlock.filedto.WallInfoBlockUpdateRequest;

import lombok.Getter;

@Getter
public class DataUpdateRequest {

	private String category;
	private Long memberId;
	private Long spaceId;
	private Long spaceWallId;
	private String shareURL;
	private WallInfoBlockUpdateRequest wallInfoBlock;
	private List<BlockSaveRequest<?>> blocks;
	private StyleSettingUpdateRequest styleSetting;

	private DataUpdateRequest(){

	}

	public DataUpdateRequest(final String category, final Long spaceWallId, final Long spaceId, final Long memberId, final String shareURL, final WallInfoBlockUpdateRequest wallInfoBlock, final List<BlockSaveRequest<?>> blocks, final StyleSettingUpdateRequest styleSetting) {
		this.category = category;
		this.spaceWallId = spaceWallId;
		this.spaceId = spaceId;
		this.memberId = memberId;
		this.shareURL = shareURL;
		this.wallInfoBlock = wallInfoBlock;
		this.blocks = blocks;
		this.styleSetting = styleSetting;
	}
}
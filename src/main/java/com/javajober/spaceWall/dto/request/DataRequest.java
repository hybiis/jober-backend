package com.javajober.spaceWall.dto.request;

import java.util.List;

import com.javajober.setting.dto.StyleSettingSaveRequest;
import com.javajober.wallInfoBlock.dto.request.WallInfoBlockRequest;
import lombok.Getter;

@Getter
public class DataRequest {
	private String category;
	private Long memberId;
	private String shareURL;
	private WallInfoBlockRequest wallInfoBlock;
	private List<BlockRequest> blocks;
	private StyleSettingSaveRequest styleSetting;
  
	private DataRequest() {
	}

	public DataRequest(final String category, final Long memberId, final String shareURL, final WallInfoBlockRequest wallInfoBlock, final List<BlockRequest> blocks, final StyleSettingSaveRequest styleSetting) {
		this.category = category;
		this.memberId = memberId;
		this.shareURL = shareURL;
		this.wallInfoBlock = wallInfoBlock;
		this.blocks = blocks;
		this.styleSetting = styleSetting;
	}
}
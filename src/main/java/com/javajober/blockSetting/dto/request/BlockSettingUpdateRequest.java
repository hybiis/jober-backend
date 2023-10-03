package com.javajober.blockSetting.dto.request;

import com.javajober.blockSetting.domain.BlockSetting;

import lombok.Getter;

@Getter
public class BlockSettingUpdateRequest {
	private Long blockSettingId;
	private String shape;
	private String style;
	private String styleColor;
	private boolean gradation;

	public BlockSettingUpdateRequest(){

	}

	public BlockSetting toEntity(BlockSettingUpdateRequest updateRequest) {
		return BlockSetting.builder()
			.shape(updateRequest.getShape())
			.style(updateRequest.getStyle())
			.styleColor(updateRequest.getStyleColor())
			.gradation(updateRequest.isGradation())
			.build();
	}
}

package com.javajober.blocks.styleSetting.blockSetting.dto.request;

import com.javajober.blocks.styleSetting.blockSetting.domain.BlockSetting;

import lombok.Getter;

@Getter
public class BlockSettingUpdateRequest {

	private Long blockSettingBlockId;
	private String shape;
	private String style;
	private String styleColor;
	private Boolean gradation;

	public BlockSettingUpdateRequest(){

	}

	public BlockSetting toEntity(final BlockSettingUpdateRequest updateRequest) {
		return BlockSetting.builder()
			.shape(updateRequest.getShape())
			.style(updateRequest.getStyle())
			.styleColor(updateRequest.getStyleColor())
			.gradation(updateRequest.getGradation())
			.build();
	}
}
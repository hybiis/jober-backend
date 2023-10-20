package com.javajober.blocks.styleSetting.blockSetting.dto.request;

import com.javajober.blocks.styleSetting.blockSetting.domain.BlockSetting;

import lombok.Getter;

@Getter
public class BlockSettingSaveRequest {

	private String shape;
	private String style;
	private String styleColor;
	private Boolean gradation;

	public BlockSettingSaveRequest(){

	}

	public BlockSetting toEntity() {
		return BlockSetting.builder()
				.shape(this.getShape())
				.style(this.getStyle())
				.styleColor(this.getStyleColor())
				.gradation(this.getGradation())
				.build();
	}
}
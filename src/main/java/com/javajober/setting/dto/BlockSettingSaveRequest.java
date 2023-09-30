package com.javajober.setting.dto;

import com.javajober.setting.domain.BlockSetting;

import lombok.Getter;

@Getter
public class BlockSettingSaveRequest {

	private String shape;
	private String style;
	private String styleColor;
	private boolean gradation;

	public BlockSettingSaveRequest(){

	}

	public BlockSetting toEntity() {
		return BlockSetting.builder()
			.shape(this.getShape())
			.style(this.getStyle())
			.styleColor(this.getStyleColor())
			.gradation(this.isGradation())
			.build();
	}

}

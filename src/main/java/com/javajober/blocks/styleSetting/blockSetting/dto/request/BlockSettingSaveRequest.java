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

	public static BlockSetting toEntity(final BlockSettingSaveRequest request) {
		return BlockSetting.builder()
				.shape(request.getShape())
				.style(request.getStyle())
				.styleColor(request.getStyleColor())
				.gradation(request.getGradation())
				.build();
	}
}
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

	public static BlockSetting toEntity(BlockSettingSaveRequest blockSettingSaveRequest){
		return BlockSetting.builder()
			.shape(blockSettingSaveRequest.getShape())
			.style(blockSettingSaveRequest.getStyle())
			.styleColor(blockSettingSaveRequest.getStyleColor())
			.gradation(blockSettingSaveRequest.isGradation())
			.build();

	}

}

package com.javajober.setting.dto;

import com.javajober.setting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingSaveRequest {
	private String solidColor;
	private boolean gradation;
	private String styleImageURL;

	public BackgroundSettingSaveRequest(){

	}

	public static BackgroundSetting toEntity(BackgroundSettingSaveRequest backgroundSettingSaveRequest){
		return BackgroundSetting.builder()
			.solidColor(backgroundSettingSaveRequest.getSolidColor())
			.gradation(backgroundSettingSaveRequest.isGradation())
			.styleImageURL(backgroundSettingSaveRequest.getStyleImageURL())
			.build();
	}
}

package com.javajober.setting.dto;

import com.javajober.setting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingSaveRequest {
	private String solidColor;
	private boolean gradation;

	public BackgroundSettingSaveRequest(){

	}
	public BackgroundSetting toEntity() {
		return BackgroundSetting.builder()
			.solidColor(this.getSolidColor())
			.gradation(this.isGradation())
			.build();
	}
}

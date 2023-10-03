package com.javajober.backgroundSetting.dto.request;

import com.javajober.backgroundSetting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingUpdateRequest {
	private Long backgroundSettingId;
	private String solidColor;
	private boolean gradation;

	public BackgroundSettingUpdateRequest(){

	}
	public BackgroundSetting toEntity(BackgroundSettingUpdateRequest updateRequest) {
		return BackgroundSetting.builder()
			.solidColor(updateRequest.getSolidColor())
			.gradation(updateRequest.isGradation())
			.build();
	}
}

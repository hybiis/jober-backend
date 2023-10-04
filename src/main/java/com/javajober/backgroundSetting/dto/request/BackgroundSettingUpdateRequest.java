package com.javajober.backgroundSetting.dto.request;

import com.javajober.backgroundSetting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingUpdateRequest {

	private Long backgroundSettingBlockId;
	private String solidColor;
	private Boolean gradation;

	public BackgroundSettingUpdateRequest(){

	}
	public BackgroundSetting toEntity(BackgroundSettingUpdateRequest updateRequest) {
		return BackgroundSetting.builder()
			.solidColor(updateRequest.getSolidColor())
			.gradation(updateRequest.getGradation())
			.build();
	}
}

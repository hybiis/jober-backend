package com.javajober.styleSetting.dto.request;

import com.javajober.backgroundSetting.dto.request.BackgroundSettingUpdateRequest;
import com.javajober.blockSetting.dto.request.BlockSettingUpdateRequest;
import com.javajober.themeSetting.dto.request.ThemeSettingUpdateRequest;

import lombok.Getter;

@Getter
public class StyleSettingUpdateRequest {
	private Long styleSettingId;
	private BackgroundSettingUpdateRequest backgroundSetting;
	private BlockSettingUpdateRequest blockSetting;
	private ThemeSettingUpdateRequest themeSetting;

	public StyleSettingUpdateRequest() {

	}

	public StyleSettingUpdateRequest(final BackgroundSettingUpdateRequest backgroundSetting,
		final BlockSettingUpdateRequest blockSetting, final ThemeSettingUpdateRequest themeSetting) {
		this.backgroundSetting = backgroundSetting;
		this.blockSetting = blockSetting;
		this.themeSetting = themeSetting;
	}
}

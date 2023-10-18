package com.javajober.styleSetting.dto.request;

import com.javajober.backgroundSetting.dto.request.BackgroundStringUpdateRequest;
import com.javajober.blockSetting.dto.request.BlockSettingUpdateRequest;
import com.javajober.themeSetting.dto.request.ThemeSettingUpdateRequest;

import lombok.Getter;

@Getter
public class StyleSettingStringUpdateRequest {

	private Long styleSettingBlockId;
	private BackgroundStringUpdateRequest backgroundSetting;
	private BlockSettingUpdateRequest blockSetting;
	private ThemeSettingUpdateRequest themeSetting;

	public StyleSettingStringUpdateRequest() {

	}
}
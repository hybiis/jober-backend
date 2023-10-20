package com.javajober.blocks.styleSetting.dto.request;

import com.javajober.blocks.styleSetting.backgroundSetting.dto.request.BackgroundStringUpdateRequest;
import com.javajober.blocks.styleSetting.blockSetting.dto.request.BlockSettingUpdateRequest;
import com.javajober.blocks.styleSetting.themeSetting.dto.request.ThemeSettingUpdateRequest;

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
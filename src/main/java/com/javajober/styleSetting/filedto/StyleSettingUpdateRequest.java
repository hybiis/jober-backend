package com.javajober.styleSetting.filedto;

import com.javajober.backgroundSetting.filedto.BackgroundSettingUpdateRequest;
import com.javajober.blockSetting.dto.request.BlockSettingUpdateRequest;
import com.javajober.themeSetting.dto.request.ThemeSettingUpdateRequest;

import lombok.Getter;

@Getter
public class StyleSettingUpdateRequest {

	private Long styleSettingBlockId;
	private BackgroundSettingUpdateRequest backgroundSetting;
	private BlockSettingUpdateRequest blockSetting;
	private ThemeSettingUpdateRequest themeSetting;

	public StyleSettingUpdateRequest() {

	}
}
package com.javajober.blocks.styleSetting.filedto;

import com.javajober.blocks.styleSetting.backgroundSetting.filedto.BackgroundSettingUpdateRequest;
import com.javajober.blocks.styleSetting.blockSetting.dto.request.BlockSettingUpdateRequest;
import com.javajober.blocks.styleSetting.themeSetting.dto.request.ThemeSettingUpdateRequest;

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
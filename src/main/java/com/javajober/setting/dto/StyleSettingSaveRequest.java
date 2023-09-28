package com.javajober.setting.dto;

import com.javajober.setting.domain.BackgroundSetting;
import com.javajober.setting.domain.BlockSetting;
import com.javajober.setting.domain.StyleSetting;
import com.javajober.setting.domain.ThemeSetting;

import lombok.Getter;

@Getter
public class StyleSettingSaveRequest {

	private Long backgroundSettingId;
	private Long blockSettingId;
	private Long themeSettingId;

	public StyleSettingSaveRequest(){

	}

	public static StyleSetting toEntity(final BackgroundSetting backgroundSetting, final BlockSetting blockSetting, final ThemeSetting themeSetting){
		return StyleSetting.builder()
			.backgroundSetting(backgroundSetting)
			.blockSetting(blockSetting)
			.themeSetting(themeSetting)
			.build();
	}

}

package com.javajober.setting.dto;
 ;
import com.javajober.setting.domain.StyleSetting;

import lombok.Getter;

@Getter
public class StyleSettingSaveRequest {

	private BackgroundSettingSaveRequest backgroundSetting;
	private BlockSettingSaveRequest blockSetting;
	private ThemeSettingSaveRequest themeSetting;

	public StyleSettingSaveRequest(){

	}


	public StyleSetting toEntity() {
		return StyleSetting.builder()
			.backgroundSetting(backgroundSetting.toEntity())
			.blockSetting(blockSetting.toEntity())
			.themeSetting(themeSetting.toEntity())
			.build();
	}
}

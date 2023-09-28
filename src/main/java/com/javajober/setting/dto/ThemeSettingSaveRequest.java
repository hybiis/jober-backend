package com.javajober.setting.dto;

import com.javajober.setting.domain.ThemeSetting;

import lombok.Getter;

@Getter
public class ThemeSettingSaveRequest {
	private String theme;

	public ThemeSettingSaveRequest(){

	}

	public static ThemeSetting toEntity(ThemeSettingSaveRequest themeSettingSaveRequest){
		return ThemeSetting.builder()
			.theme(themeSettingSaveRequest.getTheme())
			.build();
	}
}

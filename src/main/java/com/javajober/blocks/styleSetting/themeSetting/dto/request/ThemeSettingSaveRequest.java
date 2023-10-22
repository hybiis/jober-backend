package com.javajober.blocks.styleSetting.themeSetting.dto.request;

import com.javajober.blocks.styleSetting.themeSetting.domain.ThemeSetting;

import lombok.Getter;

@Getter
public class ThemeSettingSaveRequest {

	private String theme;

	public ThemeSettingSaveRequest(){

	}

	public static ThemeSetting toEntity(ThemeSettingSaveRequest request) {
		return ThemeSetting.builder()
			.theme(request.getTheme())
			.build();
	}
}
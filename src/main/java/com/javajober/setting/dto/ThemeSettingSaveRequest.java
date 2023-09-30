package com.javajober.setting.dto;

import com.javajober.setting.domain.ThemeSetting;

import lombok.Getter;

@Getter
public class ThemeSettingSaveRequest {
	private String theme;

	public ThemeSettingSaveRequest(){

	}

	public ThemeSetting toEntity() {
		return ThemeSetting.builder()
			.theme(this.getTheme())
			.build();
	}
}

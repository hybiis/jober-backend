package com.javajober.themeSetting.dto.request;

import com.javajober.themeSetting.domain.ThemeSetting;

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
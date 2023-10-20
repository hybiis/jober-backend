package com.javajober.blocks.styleSetting.themeSetting.dto.request;

import com.javajober.blocks.styleSetting.themeSetting.domain.ThemeSetting;

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
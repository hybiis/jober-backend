package com.javajober.themeSetting.dto.request;

import com.javajober.themeSetting.domain.ThemeSetting;

import lombok.Getter;

@Getter
public class ThemeSettingUpdateRequest {
	private Long themeSettingId;
	private String theme;

	public ThemeSettingUpdateRequest(){

	}

	public ThemeSetting toEntity(ThemeSettingUpdateRequest updateRequest) {
		return ThemeSetting.builder()
			.theme(updateRequest.getTheme())
			.build();
	}
}

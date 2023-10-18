package com.javajober.themeSetting.dto.request;

import com.javajober.themeSetting.domain.ThemeSetting;

import lombok.Getter;

@Getter
public class ThemeSettingUpdateRequest {

	private Long themeSettingBlockId;
	private String theme;

	public ThemeSettingUpdateRequest() {

	}

	public ThemeSetting toEntity(final ThemeSettingUpdateRequest updateRequest) {
		return ThemeSetting.builder()
				.theme(updateRequest.getTheme())
				.build();
	}
}
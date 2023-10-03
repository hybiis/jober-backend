package com.javajober.themeSetting.dto.response;

import com.javajober.themeSetting.domain.ThemeSetting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ThemeSettingResponse {

    private Long themeSettingId;
    private String theme;

    private ThemeSettingResponse(){}

    @Builder
    public ThemeSettingResponse(final Long themeSettingId, final String theme) {
        this.themeSettingId = themeSettingId;
        this.theme = theme;
    }

    public static ThemeSettingResponse from(ThemeSetting themeSetting) {
        return ThemeSettingResponse.builder()
                .themeSettingId(themeSetting.getId())
                .theme(themeSetting.getTheme())
                .build();
    }
}

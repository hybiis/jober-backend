package com.javajober.blocks.styleSetting.themeSetting.dto.response;

import com.javajober.blocks.styleSetting.themeSetting.domain.ThemeSetting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ThemeSettingResponse {

    private Long themeSettingBlockId;
    private String theme;

    private ThemeSettingResponse(){

    }

    @Builder
    public ThemeSettingResponse(final Long themeSettingBlockId, final String theme) {
        this.themeSettingBlockId = themeSettingBlockId;
        this.theme = theme;
    }

    public static ThemeSettingResponse from(final ThemeSetting themeSetting) {
        return ThemeSettingResponse.builder()
                .themeSettingBlockId(themeSetting.getId())
                .theme(themeSetting.getTheme())
                .build();
    }
}
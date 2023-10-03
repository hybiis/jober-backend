package com.javajober.styleSetting.dto.response;

import com.javajober.backgroundSetting.dto.response.BackgroundSettingResponse;
import com.javajober.blockSetting.dto.response.BlockSettingResponse;
import com.javajober.themeSetting.dto.response.ThemeSettingResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StyleSettingResponse {

    private BackgroundSettingResponse backgroundSettingResponse;
    private BlockSettingResponse blockSettingResponse;
    private ThemeSettingResponse themeSettingResponse;

    public StyleSettingResponse() {}

    @Builder
    public StyleSettingResponse(final BackgroundSettingResponse backgroundSettingResponse, final BlockSettingResponse blockSettingResponse, final ThemeSettingResponse themeSettingResponse) {
        this.backgroundSettingResponse = backgroundSettingResponse;
        this.blockSettingResponse = blockSettingResponse;
        this.themeSettingResponse = themeSettingResponse;
    }

    public static StyleSettingResponse from(final BackgroundSettingResponse backgroundSettingResponse,
                                            final BlockSettingResponse blockSettingResponse,
                                            final ThemeSettingResponse themeSettingResponse) {
        return StyleSettingResponse.builder()
                .backgroundSettingResponse(backgroundSettingResponse)
                .blockSettingResponse(blockSettingResponse)
                .themeSettingResponse(themeSettingResponse)
                .build();
    }
}

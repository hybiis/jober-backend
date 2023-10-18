package com.javajober.styleSetting.dto.response;

import com.javajober.backgroundSetting.dto.response.BackgroundSettingResponse;
import com.javajober.blockSetting.dto.response.BlockSettingResponse;
import com.javajober.styleSetting.domain.StyleSetting;
import com.javajober.themeSetting.dto.response.ThemeSettingResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StyleSettingResponse {

    private Long styleSettingBlockId;
    private BackgroundSettingResponse backgroundSetting;
    private BlockSettingResponse blockSetting;
    private ThemeSettingResponse themeSetting;

    public StyleSettingResponse() {
        
    }

    @Builder
    public StyleSettingResponse(final Long styleSettingBlockId, final BackgroundSettingResponse backgroundSetting,
                                final BlockSettingResponse blockSetting, final ThemeSettingResponse themeSetting) {

        this.styleSettingBlockId = styleSettingBlockId;
        this.backgroundSetting = backgroundSetting;
        this.blockSetting = blockSetting;
        this.themeSetting = themeSetting;
    }
}

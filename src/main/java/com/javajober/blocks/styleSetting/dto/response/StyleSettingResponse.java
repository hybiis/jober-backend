package com.javajober.blocks.styleSetting.dto.response;

import com.javajober.blocks.styleSetting.backgroundSetting.dto.response.BackgroundSettingResponse;
import com.javajober.blocks.styleSetting.blockSetting.dto.response.BlockSettingResponse;
import com.javajober.blocks.styleSetting.themeSetting.dto.response.ThemeSettingResponse;
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

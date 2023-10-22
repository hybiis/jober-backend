package com.javajober.blocks.styleSetting.dto.request;

import com.javajober.blocks.styleSetting.backgroundSetting.domain.BackgroundSetting;
import com.javajober.blocks.styleSetting.backgroundSetting.dto.request.BackgroundSettingStringSaveRequest;
import com.javajober.blocks.styleSetting.blockSetting.domain.BlockSetting;
import com.javajober.blocks.styleSetting.blockSetting.dto.request.BlockSettingSaveRequest;
import com.javajober.blocks.styleSetting.domain.StyleSetting;
import com.javajober.blocks.styleSetting.themeSetting.domain.ThemeSetting;
import com.javajober.blocks.styleSetting.themeSetting.dto.request.ThemeSettingSaveRequest;
import lombok.Getter;

@Getter
public class StyleSettingStringSaveRequest {

    private BackgroundSettingStringSaveRequest backgroundSetting;
    private BlockSettingSaveRequest blockSetting;
    private ThemeSettingSaveRequest themeSetting;

    public StyleSettingStringSaveRequest(){

    }

    public StyleSetting toEntity(final BackgroundSetting backgroundSetting, final BlockSetting blockSetting, final ThemeSetting themeSetting) {
        return StyleSetting.builder()
                .backgroundSetting(backgroundSetting)
                .blockSetting(blockSetting)
                .themeSetting(themeSetting)
                .build();
    }
}
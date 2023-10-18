package com.javajober.styleSetting.dto.request;

import com.javajober.backgroundSetting.domain.BackgroundSetting;
import com.javajober.backgroundSetting.dto.request.BackgroundStringSaveRequest;
import com.javajober.blockSetting.domain.BlockSetting;
import com.javajober.blockSetting.dto.request.BlockSettingSaveRequest;
import com.javajober.styleSetting.domain.StyleSetting;
import com.javajober.themeSetting.domain.ThemeSetting;
import com.javajober.themeSetting.dto.request.ThemeSettingSaveRequest;
import lombok.Getter;

@Getter
public class StyleSettingStringSaveRequest {

    private BackgroundStringSaveRequest backgroundSetting;
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
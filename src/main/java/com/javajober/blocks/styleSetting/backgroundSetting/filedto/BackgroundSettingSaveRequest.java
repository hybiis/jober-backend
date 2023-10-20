package com.javajober.blocks.styleSetting.backgroundSetting.filedto;

import com.javajober.blocks.styleSetting.backgroundSetting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingSaveRequest {
    private String solidColor;
    private Boolean gradation;

    public BackgroundSettingSaveRequest() {

    }

    public BackgroundSetting toEntity(final String styleImgName) {
        return BackgroundSetting.builder()
                .solidColor(this.getSolidColor())
                .gradation(this.getGradation())
                .styleImageURL(styleImgName)
                .build();
    }
}
package com.javajober.backgroundSetting.filedto;

import com.javajober.backgroundSetting.domain.BackgroundSetting;

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
package com.javajober.blocks.styleSetting.backgroundSetting.filedto;

import com.javajober.blocks.styleSetting.backgroundSetting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingSaveRequest {
    private String solidColor;
    private Boolean gradation;

    public BackgroundSettingSaveRequest() {

    }

    public static BackgroundSetting toEntity(final BackgroundSettingSaveRequest request, final String styleImgName) {
        return BackgroundSetting.builder()
                .solidColor(request.getSolidColor())
                .gradation(request.getGradation())
                .styleImageURL(styleImgName)
                .build();
    }
}
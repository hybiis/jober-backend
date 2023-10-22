package com.javajober.blocks.styleSetting.backgroundSetting.dto.request;

import com.javajober.blocks.styleSetting.backgroundSetting.domain.BackgroundSetting;

import lombok.Getter;

@Getter
public class BackgroundSettingStringSaveRequest {

    private String solidColor;
    private Boolean gradation;
    private String styleImgURL;

    public BackgroundSettingStringSaveRequest() {

    }

    public static BackgroundSetting toEntity(final BackgroundSettingStringSaveRequest request) {

        return BackgroundSetting.builder()
                .solidColor(request.getSolidColor())
                .gradation(request.getGradation())
                .styleImageURL(request.getStyleImgURL())
                .build();
    }
}
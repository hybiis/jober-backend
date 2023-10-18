package com.javajober.backgroundSetting.dto.request;

import com.javajober.backgroundSetting.domain.BackgroundSetting;
import lombok.Getter;

@Getter
public class BackgroundStringSaveRequest {

    private String solidColor;
    private Boolean gradation;
    private String styleImgURL;

    public BackgroundStringSaveRequest() {

    }

    public BackgroundSetting toEntity() {
        return BackgroundSetting.builder()
                .solidColor(this.getSolidColor())
                .gradation(this.getGradation())
                .styleImageURL(this.getStyleImgURL())
                .build();
    }
}
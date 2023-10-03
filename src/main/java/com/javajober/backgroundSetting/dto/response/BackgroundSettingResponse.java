package com.javajober.backgroundSetting.dto.response;

import com.javajober.backgroundSetting.domain.BackgroundSetting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BackgroundSettingResponse {

    private Long backgroundSettingId;
    private String solidColor;
    private boolean gradation;
    private String styleImgURL;

    private BackgroundSettingResponse() {}

    @Builder
    public BackgroundSettingResponse(final Long backgroundSettingId, final String solidColor, final boolean gradation, final String styleImgURL) {
        this.backgroundSettingId = backgroundSettingId;
        this.solidColor = solidColor;
        this.gradation = gradation;
        this.styleImgURL = styleImgURL;
    }

    public static BackgroundSettingResponse from(BackgroundSetting backgroundSetting) {
        return BackgroundSettingResponse.builder()
                .backgroundSettingId(backgroundSetting.getId())
                .solidColor(backgroundSetting.getSolidColor())
                .gradation(backgroundSetting.getGradation())
                .styleImgURL(backgroundSetting.getStyleImageURL())
                .build();
    }
}

package com.javajober.backgroundSetting.dto.response;

import com.javajober.backgroundSetting.domain.BackgroundSetting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BackgroundSettingResponse {

    private Long backgroundSettingBlockId;
    private String solidColor;
    private boolean gradation;
    private String styleImgURL;

    private BackgroundSettingResponse() {

    }

    @Builder
    public BackgroundSettingResponse(final Long backgroundSettingBlockId, final String solidColor, final boolean gradation, final String styleImgURL) {
        this.backgroundSettingBlockId = backgroundSettingBlockId;
        this.solidColor = solidColor;
        this.gradation = gradation;
        this.styleImgURL = styleImgURL;
    }

    public static BackgroundSettingResponse from(final BackgroundSetting backgroundSetting) {
        return BackgroundSettingResponse.builder()
                .backgroundSettingBlockId(backgroundSetting.getId())
                .solidColor(backgroundSetting.getSolidColor())
                .gradation(backgroundSetting.getGradation())
                .styleImgURL(backgroundSetting.getStyleImageURL())
                .build();
    }
}
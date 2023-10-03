package com.javajober.blockSetting.dto.response;

import com.javajober.blockSetting.domain.BlockSetting;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlockSettingResponse {

    private Long blockSettingId;
    private String shape;
    private String style;
    private String styleColor;
    private boolean gradation;

    private BlockSettingResponse(){}

    @Builder
    public BlockSettingResponse(final Long blockSettingId, final String shape, final String style, final String styleColor, final boolean gradation) {
        this.blockSettingId = blockSettingId;
        this.shape = shape;
        this.style = style;
        this.styleColor = styleColor;
        this.gradation = gradation;
    }

    public static BlockSettingResponse from(BlockSetting blockSetting) {
        return BlockSettingResponse.builder()
                .blockSettingId(blockSetting.getId())
                .shape(blockSetting.getShape())
                .style(blockSetting.getStyle())
                .styleColor(blockSetting.getStyleColor())
                .gradation(blockSetting.getGradation())
                .build();
    }
}

package com.javajober.wallInfoBlock.dto.request;

import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import lombok.Getter;

@Getter
public class WallInfoBlockStringSaveRequest {

    private String wallInfoTitle;
    private String wallInfoDescription;
    private String backgroundImgURL;
    private String wallInfoImgURL;

    private WallInfoBlockStringSaveRequest() {

    }

    public WallInfoBlockStringSaveRequest(final String wallInfoTitle, final String wallInfoDescription, final String backgroundImgURL,
                                          final String wallInfoImgURL) {
        this.wallInfoTitle = wallInfoTitle;
        this.wallInfoDescription = wallInfoDescription;
        this.backgroundImgURL = backgroundImgURL;
        this.wallInfoImgURL = wallInfoImgURL;
    }

    public static WallInfoBlock toEntity(final WallInfoBlockStringSaveRequest wallInfoBlockStringSaveRequest) {
        return WallInfoBlock.builder()
                .wallInfoTitle(wallInfoBlockStringSaveRequest.getWallInfoTitle())
                .wallInfoDescription(wallInfoBlockStringSaveRequest.getWallInfoDescription())
                .wallInfoBackgroundImageUrl(wallInfoBlockStringSaveRequest.getBackgroundImgURL())
                .wallInfoProfileImageUrl(wallInfoBlockStringSaveRequest.getWallInfoImgURL())
                .build();
    }
}
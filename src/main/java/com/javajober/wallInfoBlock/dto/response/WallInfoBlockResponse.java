package com.javajober.wallInfoBlock.dto.response;

import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WallInfoBlockResponse {

    private Long wallInfoBlockId;
    private String wallInfoTitle;
    private String wallInfoDescription;
    private String wallInfoImgURL;
    private String backgroundImgURL;

    public WallInfoBlockResponse() {

    }

    @Builder
    public WallInfoBlockResponse(final Long wallInfoBlockId, final String wallInfoTitle, final String wallInfoDescription,
                                 final String wallInfoImgURL, final String backgroundImgURL) {
        this.wallInfoBlockId = wallInfoBlockId;
        this.wallInfoTitle = wallInfoTitle;
        this.wallInfoDescription = wallInfoDescription;
        this.wallInfoImgURL = wallInfoImgURL;
        this.backgroundImgURL = backgroundImgURL;
    }

    public static WallInfoBlockResponse from(final WallInfoBlock wallInfoBlock) {
        return WallInfoBlockResponse.builder()
                .wallInfoBlockId(wallInfoBlock.getId())
                .wallInfoTitle(wallInfoBlock.getWallInfoTitle())
                .wallInfoDescription(wallInfoBlock.getWallInfoDescription())
                .wallInfoImgURL(wallInfoBlock.getWallInfoProfileImageUrl())
                .backgroundImgURL(wallInfoBlock.getWallInfoBackgroundImageUrl())
                .build();
    }
}
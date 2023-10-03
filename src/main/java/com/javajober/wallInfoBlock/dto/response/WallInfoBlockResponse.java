package com.javajober.wallInfoBlock.dto.response;

import com.javajober.wallInfoBlock.domain.WallInfoBlock;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WallInfoBlockResponse {

    private Long wallInfoBlockId;
    private String wallInfoTitle;
    private String wallInfoDescription;

    public WallInfoBlockResponse() {
    }

    @Builder
    public WallInfoBlockResponse(final Long wallInfoBlockId, final String wallInfoTitle, final String wallInfoDescription) {
        this.wallInfoBlockId = wallInfoBlockId;
        this.wallInfoTitle = wallInfoTitle;
        this.wallInfoDescription = wallInfoDescription;
    }

    public static WallInfoBlockResponse from(final WallInfoBlock wallInfoBlock) {
        return WallInfoBlockResponse.builder()
                .wallInfoBlockId(wallInfoBlock.getId())
                .wallInfoTitle(wallInfoBlock.getWallInfoTitle())
                .wallInfoDescription(wallInfoBlock.getWallInfoDescription())
                .build();
    }
}

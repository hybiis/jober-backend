package com.javajober.spaceWall.dto.response;

import com.javajober.core.util.CommonResponse;
import com.javajober.styleSetting.dto.response.StyleSettingResponse;
import com.javajober.wallInfoBlock.dto.response.WallInfoBlockResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SpaceWallResponse {

    private String category;
    private Long memberId;
    private Long addSpaceId;
    private String shareURL;
    private WallInfoBlockResponse wallInfoBlockResponse;
    private List<BlockResponse<CommonResponse>> blocks;
    private StyleSettingResponse styleSetting;

    private SpaceWallResponse() {}

    @Builder
    public SpaceWallResponse(final String category, final Long memberId, final Long addSpaceId,
                             final String shareURL, final WallInfoBlockResponse wallInfoBlockResponse, final List<BlockResponse<CommonResponse>> blocks,
                             final StyleSettingResponse styleSetting) {
        this.category = category;
        this.memberId = memberId;
        this.addSpaceId = addSpaceId;
        this.shareURL = shareURL;
        this.wallInfoBlockResponse = wallInfoBlockResponse;
        this.blocks = blocks;
        this.styleSetting = styleSetting;
    }

    public static SpaceWallResponse from(final String category, final Long memberId, final Long addSpaceId,
                                         final String shareURL, final WallInfoBlockResponse wallInfoBlockResponse, final List<BlockResponse<CommonResponse>> blocks,
                                         final StyleSettingResponse styleSetting) {
        return SpaceWallResponse.builder()
                .category(category)
                .memberId(memberId)
                .addSpaceId(addSpaceId)
                .shareURL(shareURL)
                .wallInfoBlockResponse(wallInfoBlockResponse)
                .blocks(blocks)
                .styleSetting(styleSetting)
                .build();
    }
}

package com.javajober.spaceWall.dto.response;

import com.javajober.core.util.response.CommonResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SpaceWallResponse {

    private String category;
    private Long memberId;
    private Long spaceId;
    private String shareURL;
    private CommonResponse wallInfoBlock;
    private List<BlockResponse<CommonResponse>> blocks;
    private CommonResponse styleSetting;

    private SpaceWallResponse() {}

    @Builder
    public SpaceWallResponse(final String category, final Long memberId, final Long spaceId, final String shareURL, final CommonResponse wallInfoBlock,
                             final List<BlockResponse<CommonResponse>> blocks, final CommonResponse styleSetting) {

        this.category = category;
        this.memberId = memberId;
        this.spaceId = spaceId;
        this.shareURL = shareURL;
        this.wallInfoBlock = wallInfoBlock;
        this.blocks = blocks;
        this.styleSetting = styleSetting;
    }

}
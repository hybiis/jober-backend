package com.javajober.spaceWall.dto.response;

import com.javajober.core.util.response.CommonResponse;
import com.javajober.blocks.styleSetting.dto.response.StyleSettingResponse;
import com.javajober.blocks.wallInfoBlock.dto.response.WallInfoBlockResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SpaceWallResponse {

    private String category;
    private Long memberId;
    private Long spaceId;
    private String shareURL;
    private WallInfoBlockResponse wallInfoBlock;
    private List<BlockResponse<CommonResponse>> blocks;
    private StyleSettingResponse styleSetting;

    private SpaceWallResponse() {}

    @Builder
    public SpaceWallResponse(final String category, final Long memberId, final Long spaceId,
                             final String shareURL, final WallInfoBlockResponse wallInfoBlock, final List<BlockResponse<CommonResponse>> blocks,
                             final StyleSettingResponse styleSetting) {
        this.category = category;
        this.memberId = memberId;
        this.spaceId = spaceId;
        this.shareURL = shareURL;
        this.wallInfoBlock = wallInfoBlock;
        this.blocks = blocks;
        this.styleSetting = styleSetting;
    }

}
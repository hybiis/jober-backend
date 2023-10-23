package com.javajober.spaceWall.dto.request;

import com.javajober.blocks.styleSetting.dto.request.StyleSettingStringSaveRequest;
import com.javajober.blocks.wallInfoBlock.dto.request.WallInfoBlockStringSaveRequest;
import com.javajober.core.util.response.CommonResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class DataStringSaveRequest {

    private String category;
    private Long memberId;
    private Long spaceId;
    private String shareURL;
    private WallInfoBlockStringSaveRequest wallInfoBlock;
    private List<BlockSaveRequest> blocks;
    private StyleSettingStringSaveRequest styleSetting;

    private DataStringSaveRequest() {
    }

    public DataStringSaveRequest(final String category, final Long memberId, Long spaceId, final String shareURL,
                                 final WallInfoBlockStringSaveRequest wallInfoBlock, final List<BlockSaveRequest> blocks,
                                 final StyleSettingStringSaveRequest styleSetting) {
        this.category = category;
        this.memberId = memberId;
        this.spaceId = spaceId;
        this.shareURL = shareURL;
        this.wallInfoBlock = wallInfoBlock;
        this.blocks = blocks;
        this.styleSetting = styleSetting;
    }
}
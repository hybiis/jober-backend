package com.javajober.spaceWall.dto.request;

import com.javajober.blocks.styleSetting.dto.request.StyleSettingStringSaveRequest;
import com.javajober.blocks.wallInfoBlock.dto.request.WallInfoBlockStringSaveRequest;

import lombok.Getter;

import java.util.List;

@Getter
public class DataStringSaveRequest {

    private String category;
    private Long spaceId;
    private String shareURL;
    private WallInfoBlockStringSaveRequest wallInfoBlock;
    private List<BlockSaveRequest<?>> blocks;
    private StyleSettingStringSaveRequest styleSetting;

    private DataStringSaveRequest() {
    }

    public DataStringSaveRequest(final String category, Long spaceId, final String shareURL,
                                 final WallInfoBlockStringSaveRequest wallInfoBlock, final List<BlockSaveRequest<?>> blocks,
                                 final StyleSettingStringSaveRequest styleSetting) {
        this.category = category;
        this.spaceId = spaceId;
        this.shareURL = shareURL;
        this.wallInfoBlock = wallInfoBlock;
        this.blocks = blocks;
        this.styleSetting = styleSetting;
    }
}
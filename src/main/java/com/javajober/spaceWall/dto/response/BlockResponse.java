package com.javajober.spaceWall.dto.response;

import com.javajober.core.util.response.CommonResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BlockResponse<T extends CommonResponse> {

    private String blockUUID;
    private String blockType;
    private List<T> subData;

    private BlockResponse() {

    }

    @Builder
    public BlockResponse(final String blockUUID, final String blockType, final List<T> subData) {
        this.blockUUID = blockUUID;
        this.blockType = blockType;
        this.subData = subData;
    }

    public static BlockResponse<CommonResponse> from(final String blockUUID, final String blockType, final List<CommonResponse> subData) {
        return BlockResponse.builder()
                .blockUUID(blockUUID)
                .blockType(blockType)
                .subData(subData)
                .build();
    }
}
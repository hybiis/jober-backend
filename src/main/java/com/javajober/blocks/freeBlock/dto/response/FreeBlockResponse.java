package com.javajober.blocks.freeBlock.dto.response;

import com.javajober.blocks.freeBlock.domain.FreeBlock;
import com.javajober.core.util.response.CommonResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FreeBlockResponse implements CommonResponse {

    private Long freeBlockId;
    private String freeTitle;
    private String freeContent;

    private FreeBlockResponse() {

    }

    @Builder
    public FreeBlockResponse(final Long freeBlockId, final String freeTitle, final String freeContent) {
        this.freeBlockId = freeBlockId;
        this.freeTitle = freeTitle;
        this.freeContent = freeContent;
    }

    public static FreeBlockResponse from(final FreeBlock freeBlock) {
        return FreeBlockResponse.builder()
                .freeBlockId(freeBlock.getId())
                .freeTitle(freeBlock.getFreeTitle())
                .freeContent(freeBlock.getFreeContent())
                .build();
    }
}
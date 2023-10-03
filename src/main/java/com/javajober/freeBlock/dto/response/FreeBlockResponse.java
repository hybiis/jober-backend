package com.javajober.freeBlock.dto.response;

import com.javajober.core.util.CommonResponse;
import com.javajober.freeBlock.domain.FreeBlock;
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

    public static FreeBlockResponse from(FreeBlock freeBlock) {
        return FreeBlockResponse.builder()
                .freeBlockId(freeBlock.getId())
                .freeTitle(freeBlock.getFreeTitle())
                .freeContent(freeBlock.getFreeContent())
                .build();
    }
}

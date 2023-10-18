package com.javajober.freeBlock.dto.request;

import com.javajober.freeBlock.domain.FreeBlock;
import lombok.Getter;

@Getter
public class FreeBlockSaveRequest {

    private String freeTitle;
    private String freeContent;

    public FreeBlockSaveRequest() {

    }

    public static FreeBlock toEntity(final FreeBlockSaveRequest freeBlockSaveRequest) {
        return FreeBlock.builder()
                .freeTitle(freeBlockSaveRequest.getFreeTitle())
                .freeContent(freeBlockSaveRequest.getFreeContent())
                .build();
    }
}
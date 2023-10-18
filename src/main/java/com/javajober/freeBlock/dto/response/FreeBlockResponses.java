package com.javajober.freeBlock.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FreeBlockResponses {

    private List<FreeBlockResponse> subData;

    private FreeBlockResponses() {

    }

    @Builder
    public FreeBlockResponses(final List<FreeBlockResponse> subData) {
        this.subData = subData;
    }
}
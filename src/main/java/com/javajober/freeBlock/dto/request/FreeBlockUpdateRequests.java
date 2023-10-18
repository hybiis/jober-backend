package com.javajober.freeBlock.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class FreeBlockUpdateRequests {

    List<FreeBlockUpdateRequest> subData;

    private FreeBlockUpdateRequests() {

    }

    public FreeBlockUpdateRequests(final List<FreeBlockUpdateRequest> subData) {
        this.subData = subData;
    }
}
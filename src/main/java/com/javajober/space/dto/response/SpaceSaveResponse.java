package com.javajober.space.dto.response;

import lombok.Getter;

@Getter
public class SpaceSaveResponse {

    private Long spaceId;

    public SpaceSaveResponse() {
    }

    public SpaceSaveResponse(Long spaceId) {
        this.spaceId = spaceId;
    }
}

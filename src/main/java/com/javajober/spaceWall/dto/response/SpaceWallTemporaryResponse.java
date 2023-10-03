package com.javajober.spaceWall.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SpaceWallTemporaryResponse {

    private Long spaceWallId;
    private boolean hasWallTemporary;

    private SpaceWallTemporaryResponse() {

    }

    @Builder
    public SpaceWallTemporaryResponse(final Long spaceWallId, final boolean hasWallTemporary) {
        this.spaceWallId = spaceWallId;
        this.hasWallTemporary = hasWallTemporary;
    }
}


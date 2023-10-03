package com.javajober.spaceWall.dto.request;

import lombok.Getter;

@Getter
public class DeleteTemporaryRequest {

    private Long memberId;
    private Long addSpaceId;

    private DeleteTemporaryRequest() {
    }

    public DeleteTemporaryRequest(final Long memberId, final Long addSpaceId) {
        this.memberId = memberId;
        this.addSpaceId = addSpaceId;
    }
}

package com.javajober.spaceWall.dto.request;

import lombok.Getter;

@Getter
public class TemporaryDeleteRequest {

    private Long memberId;
    private Long spaceId;

    private TemporaryDeleteRequest() {
    }

    public TemporaryDeleteRequest(final Long memberId, final Long spaceId) {
        this.memberId = memberId;
        this.spaceId = spaceId;
    }
}
package com.javajober.space.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SpaceResponse {

    private boolean hasWall;
    private Long spaceWallId;
    private List<MemberGroupResponse> list;

    public SpaceResponse() {
    }

    @Builder
    public SpaceResponse(final boolean hasWall, final Long spaceWallId, final List<MemberGroupResponse> list) {
        this.hasWall = hasWall;
        this.spaceWallId = spaceWallId;
        this.list = list;
    }
}
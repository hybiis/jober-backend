package com.javajober.space.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DataResponse {
    private boolean hasWall;
    private Long spaceWallId;
    private List<MemberGroupResponse> list;

    @Builder
    public DataResponse(final boolean hasWall, final Long spaceWallId, final List<MemberGroupResponse> list) {
        this.hasWall = hasWall;
        this.spaceWallId = spaceWallId;
        this.list = list;
    }
}
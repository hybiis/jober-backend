package com.javajober.space.dto.request;

import com.javajober.member.domain.Member;
import com.javajober.space.domain.AddSpace;
import com.javajober.space.domain.SpaceType;
import lombok.Getter;

@Getter
public class SpaceSaveRequest {

    private Long memberId;
    private String spaceTitle;
    private String representativeName;
    private String spaceType;

    public SpaceSaveRequest() {
    }

    public static AddSpace toEntity(SpaceSaveRequest request, Member member) {
        return AddSpace.builder()
                .spaceTitle(request.getSpaceTitle())
                .spaceType(SpaceType.findSpaceTypeByString(request.getSpaceType()))
                .representativeName(request.getRepresentativeName())
                .member(member)
                .build();
    }
}

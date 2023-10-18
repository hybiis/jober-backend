package com.javajober.spaceWall.dto.request;

import com.javajober.member.domain.Member;
import com.javajober.space.domain.AddSpace;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;
import com.javajober.spaceWallCategory.domain.SpaceWallCategoryType;
import lombok.Getter;

@Getter
public class SpaceWallStringRequest {

    private DataStringSaveRequest data;

    private SpaceWallStringRequest() {

    }

    public SpaceWallStringRequest(final DataStringSaveRequest data) {
        this.data = data;
    }

    public static SpaceWall toEntity(final SpaceWallCategoryType category, final Member member, final AddSpace addSpace, final String shareURL, final FlagType flagType, final String blocks) {
        return SpaceWall.builder()
                .spaceWallCategoryType(category)
                .member(member)
                .addSpace(addSpace)
                .shareURL(shareURL)
                .flag(flagType)
                .blocks(blocks)
                .build();
    }
}
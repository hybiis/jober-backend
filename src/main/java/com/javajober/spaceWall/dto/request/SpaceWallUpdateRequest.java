package com.javajober.spaceWall.dto.request;

import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.domain.SpaceWall;

import lombok.Getter;

@Getter
public class SpaceWallUpdateRequest {
	private DataUpdateRequest data;

	private SpaceWallUpdateRequest() {

	}
	public SpaceWallUpdateRequest(final DataUpdateRequest data) {
		this.data = data;
	}

	public static SpaceWall toEntity(final SpaceWall spaceWall, final String shareURL, final FlagType flagType, final String blocks) {
		return SpaceWall.builder()
			.spaceWallCategoryType(spaceWall.getSpaceWallCategoryType())
			.member(spaceWall.getMember())
			.addSpace(spaceWall.getAddSpace())
			.shareURL(shareURL)
			.flag(flagType)
			.blocks(blocks)
			.build();
	}
}

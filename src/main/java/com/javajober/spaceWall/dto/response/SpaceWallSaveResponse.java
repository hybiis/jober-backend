package com.javajober.spaceWall.dto.response;

import com.javajober.spaceWall.domain.SpaceWall;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SpaceWallSaveResponse {

	private Long spaceWallId;

	private SpaceWallSaveResponse() {

	}

	@Builder
	public SpaceWallSaveResponse(Long spaceWallId) {
		this.spaceWallId = spaceWallId;
	}


	public static SpaceWallSaveResponse from(final SpaceWall spaceWall) {
		return SpaceWallSaveResponse.builder()
				.spaceWallId(spaceWall.getId())
				.build();
	}
}
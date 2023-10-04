package com.javajober.spaceWall.filedto;

import lombok.Getter;

@Getter
public class SpaceWallUpdateRequest {
	private DataUpdateRequest data;

	private SpaceWallUpdateRequest() {

	}
	public SpaceWallUpdateRequest(final DataUpdateRequest data) {
		this.data = data;
	}
}

package com.javajober.home.dto.response;

import com.javajober.space.domain.AddSpace;
import com.javajober.space.domain.SpaceType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddSpaceResponse {

	private Long spaceId;
	private SpaceType spaceType;
	private String spaceTitle;

	private AddSpaceResponse() {

	}

	@Builder
	public AddSpaceResponse(final Long spaceId, final SpaceType spaceType, final String spaceTitle) {
		this.spaceId = spaceId;
		this.spaceType = spaceType;
		this.spaceTitle = spaceTitle;
	}

	public static AddSpaceResponse from(final AddSpace addSpace) {
		return AddSpaceResponse.builder()
			.spaceId(addSpace.getId())
			.spaceType(addSpace.getSpaceType())
			.spaceTitle(addSpace.getSpaceTitle())
			.build();
	}
}
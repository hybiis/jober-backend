package com.javajober.home.dto.response;

import java.util.List;
import java.util.Map;

import com.javajober.space.domain.SpaceType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HomeResponse {

	private MemberResponse member;
	private Map<SpaceType, List<AddSpaceResponse>> spaceWall;

	@Builder
	public HomeResponse(final MemberResponse member, final Map<SpaceType, List<AddSpaceResponse>> spaceWall) {
		this.member = member;
		this.spaceWall = spaceWall;
	}
}
package com.javajober.spaceWall.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DuplicateURLResponse {

	private Boolean hasDuplicateURL;

	private DuplicateURLResponse() {

	}

	@Builder
	public DuplicateURLResponse(final Boolean hasDuplicateURL) {
		this.hasDuplicateURL = hasDuplicateURL;
	}
}
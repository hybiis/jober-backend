package com.javajober.blocks.snsBlock.dto.request;

import lombok.Getter;

@Getter
public class SNSBlockUpdateRequest {

	private Long snsBlockId;
	private String snsUUID;
	private String snsType;
	private String snsURL;

	private SNSBlockUpdateRequest() {

	}
}
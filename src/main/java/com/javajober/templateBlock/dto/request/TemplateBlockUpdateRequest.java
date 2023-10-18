package com.javajober.templateBlock.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class TemplateBlockUpdateRequest {

	private Long templateBlockId;
	private String templateUUID;
	private String templateTitle;
	private String templateDescription;
	private List<Long> hasAccessTemplateAuth;
	private List<Long> hasDenyTemplateAuth;

	private TemplateBlockUpdateRequest() {

	}
}
package com.javajober.templateBlock.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class TemplateBlockDeleteRequest {

	List<Long> templateBlockIds;

	private TemplateBlockDeleteRequest() {

	}
}
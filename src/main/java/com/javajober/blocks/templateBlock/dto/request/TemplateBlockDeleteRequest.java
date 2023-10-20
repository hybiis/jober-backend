package com.javajober.blocks.templateBlock.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class TemplateBlockDeleteRequest {

	List<Long> templateBlockIds;

	private TemplateBlockDeleteRequest() {

	}
}
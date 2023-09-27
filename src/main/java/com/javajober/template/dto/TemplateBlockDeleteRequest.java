package com.javajober.template.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class TemplateBlockDeleteRequest {
	List<Long> templateBlockIds;

	private TemplateBlockDeleteRequest() {

	}
}

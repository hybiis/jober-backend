package com.javajober.template.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class TemplateBlockUpdateRequest {

	private Long id;
	private String templateUUID;
	private String templateTitle;
	private String templateDescription;
	private List<Long> hasAccessTemplateAuth;
	private List<Long> hasDenyTemplateAuth;

	private TemplateBlockUpdateRequest(){

	}
}

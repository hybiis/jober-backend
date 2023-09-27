package com.javajober.template.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateBlockResponses {

	private List<TemplateBlockResponse> subData;

	private TemplateBlockResponses(){

	}

	@Builder
	public TemplateBlockResponses(final List<TemplateBlockResponse> subData){
		this.subData = subData;
	}
}

package com.javajober.template.dto;


import java.util.List;

import com.javajober.template.domain.TemplateBlock;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateBlockResponse {
	private String templateUUID;
	private String templateTitle;
	private String templateDescription;
	private List<Long> hasAccessTemplateAuth;
	private List<Long> hasDenyTemplateAuth;

	private TemplateBlockResponse(){

	}

	@Builder
	public TemplateBlockResponse(String templateUUID, String templateTitle, String templateDescription,List<Long> hasAccessTemplateAuth,
		List<Long> hasDenyTemplateAuth){
		this.templateUUID=templateUUID;
		this.templateTitle=templateTitle;
		this.templateDescription=templateDescription;
		this.hasAccessTemplateAuth = hasAccessTemplateAuth;
		this.hasDenyTemplateAuth = hasDenyTemplateAuth;
	}

	public static TemplateBlockResponse from(TemplateBlock templateBlock,List<Long> hasAccessTemplateAuth,
		List<Long> hasDenyTemplateAuth){
		return TemplateBlockResponse.builder()
			.templateUUID(templateBlock.getTemplateUUID())
			.templateTitle(templateBlock.getTemplateTitle())
			.templateDescription(templateBlock.getTemplateDescription())
			.hasAccessTemplateAuth(hasAccessTemplateAuth)
			.hasDenyTemplateAuth(hasDenyTemplateAuth)
			.build();
	}
}

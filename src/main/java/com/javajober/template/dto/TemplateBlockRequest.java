package com.javajober.template.dto;



import java.util.ArrayList;
import java.util.List;

import com.javajober.template.domain.TemplateBlock;

import lombok.Getter;

@Getter
public class TemplateBlockRequest {
	private String templateUUID;
	private String templateTitle;
	private String templateDescription;
	private List<Long> hasAccessTemplateAuth;
	private List<Long> hasDenyTemplateAuth;

	public TemplateBlockRequest(){
	}

	public static TemplateBlock toEntity(TemplateBlockRequest templateBlockRequest){
		return TemplateBlock.builder()
			.templateUUID(templateBlockRequest.getTemplateUUID())
			.templateTitle(templateBlockRequest.getTemplateTitle())
			.templateDescription(templateBlockRequest.getTemplateDescription())
			.build();
	}
	public List<Long> getAllAuthIds() {
		List<Long> allAuthIds = new ArrayList<>();
		allAuthIds.addAll(hasAccessTemplateAuth);
		allAuthIds.addAll(hasDenyTemplateAuth);
		return allAuthIds;
	}
}


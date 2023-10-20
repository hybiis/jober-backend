package com.javajober.blocks.templateBlock.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.javajober.blocks.templateBlock.domain.TemplateBlock;

import lombok.Getter;

@Getter
public class TemplateBlockSaveRequest {

	private String templateUUID;
	private String templateTitle;
	private String templateDescription;
	private List<Long> hasAccessTemplateAuth;
	private List<Long> hasDenyTemplateAuth;

	public TemplateBlockSaveRequest(){

	}

	public static TemplateBlock toEntity(final TemplateBlockSaveRequest templateBlockSaveRequest){
		return TemplateBlock.builder()
			.templateUUID(templateBlockSaveRequest.getTemplateUUID())
			.templateTitle(templateBlockSaveRequest.getTemplateTitle())
			.templateDescription(templateBlockSaveRequest.getTemplateDescription())
			.build();
	}
	public List<Long> getAllAuthIds() {
		List<Long> allAuthIds = new ArrayList<>();
		allAuthIds.addAll(hasAccessTemplateAuth);
		allAuthIds.addAll(hasDenyTemplateAuth);
		return allAuthIds;
	}
}
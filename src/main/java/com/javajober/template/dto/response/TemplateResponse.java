package com.javajober.template.dto.response;

import java.util.List;

import com.javajober.template.domain.Template;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateResponse {

	private List<TemplateResponse.TemplateInfo> list;

	public TemplateResponse(final List<TemplateResponse.TemplateInfo> list){
		this.list=list;
	}

	@Getter
	public static class TemplateInfo {
		private Long templateId;
		private String templateTitle;
		private String templateDescription;

		@Builder
		public TemplateInfo(final Long templateId, final String templateTitle, final String templateDescription){
			this.templateId=templateId;
			this.templateTitle=templateTitle;
			this.templateDescription=templateDescription;
		}

		public static TemplateInfo from(final Template template){
			return TemplateInfo.builder()
				.templateId(template.getId())
				.templateTitle(template.getTemplateTitle())
				.templateDescription(template.getTemplateDescription())
				.build();
		}
	}
}
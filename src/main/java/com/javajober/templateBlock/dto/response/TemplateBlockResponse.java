package com.javajober.templateBlock.dto.response;

import java.util.List;

import com.javajober.core.util.CommonResponse;
import com.javajober.templateBlock.domain.TemplateBlock;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TemplateBlockResponse implements CommonResponse {

	private Long templateBlockId;
	private String templateUUID;
	private String templateTitle;
	private String templateDescription;
	private List<Long> hasAccessTemplateAuth;
	private List<Long> hasDenyTemplateAuth;

	private TemplateBlockResponse() {

	}

	@Builder
	public TemplateBlockResponse(final Long templateBlockId, final String templateUUID, final String templateTitle,
								 final String templateDescription, final List<Long> hasAccessTemplateAuth,
								 final List<Long> hasDenyTemplateAuth) {
		this.templateBlockId = templateBlockId;
		this.templateUUID = templateUUID;
		this.templateTitle = templateTitle;
		this.templateDescription = templateDescription;
		this.hasAccessTemplateAuth = hasAccessTemplateAuth;
		this.hasDenyTemplateAuth = hasDenyTemplateAuth;
	}

	public static TemplateBlockResponse of(final TemplateBlock templateBlock, final List<Long> hasAccessTemplateAuth,
										   final List<Long> hasDenyTemplateAuth) {

		return TemplateBlockResponse.builder()
				.templateBlockId(templateBlock.getId())
				.templateUUID(templateBlock.getTemplateUUID())
				.templateTitle(templateBlock.getTemplateTitle())
				.templateDescription(templateBlock.getTemplateDescription())
				.hasAccessTemplateAuth(hasAccessTemplateAuth)
				.hasDenyTemplateAuth(hasDenyTemplateAuth)
				.build();
	}
}
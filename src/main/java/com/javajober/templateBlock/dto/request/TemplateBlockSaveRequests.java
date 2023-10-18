package com.javajober.templateBlock.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class TemplateBlockSaveRequests<T> {

	List<T> subData;

	private TemplateBlockSaveRequests(){

	}

	public TemplateBlockSaveRequests(final List<T> subData) {
		this.subData = subData;
	}
}
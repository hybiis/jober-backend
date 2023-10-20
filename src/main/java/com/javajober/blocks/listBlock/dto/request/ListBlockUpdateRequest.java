package com.javajober.blocks.listBlock.dto.request;

import com.javajober.blocks.listBlock.domain.ListBlock;

import lombok.Getter;

@Getter
public class ListBlockUpdateRequest {

	private Long listBlockId;
	private String listUUID;
	private String listLabel;
	private String listTitle;
	private String listDescription;
	private Boolean	isLink;

	private ListBlockUpdateRequest() {

	}

	public static ListBlock toEntity(final ListBlockUpdateRequest listBlockRequest) {
		return ListBlock.builder()
			.listUUID(listBlockRequest.getListUUID())
			.listLabel(listBlockRequest.getListLabel())
			.listTitle(listBlockRequest.getListTitle())
			.listDescription(listBlockRequest.getListDescription())
			.isLink(listBlockRequest.getIsLink())
			.build();
	}
}
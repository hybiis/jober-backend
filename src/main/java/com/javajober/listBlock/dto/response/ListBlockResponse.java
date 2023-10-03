package com.javajober.listBlock.dto.response;

import com.javajober.core.util.CommonResponse;
import com.javajober.listBlock.domain.ListBlock;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListBlockResponse implements CommonResponse {

    private Long listBlockId;
    private String listLabel;
    private String listTitle;
    private String listDescription;
    private boolean isLink;

    private ListBlockResponse(){}

    @Builder
    public ListBlockResponse(final Long listBlockId, final String listLabel, final String listTitle,
                             final String listDescription, final boolean isLink) {
        this.listBlockId = listBlockId;
        this.listLabel = listLabel;
        this.listTitle = listTitle;
        this.listDescription = listDescription;
        this.isLink = isLink;
    }

    public static ListBlockResponse from(ListBlock listBlock) {
        return ListBlockResponse.builder()
                .listBlockId(listBlock.getId())
                .listLabel(listBlock.getListLabel())
                .listTitle(listBlock.getListTitle())
                .listDescription(listBlock.getListDescription())
                .isLink(listBlock.isLink())
                .build();

    }
}

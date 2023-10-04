package com.javajober.listBlock.dto.response;

import com.javajober.core.util.CommonResponse;
import com.javajober.listBlock.domain.ListBlock;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListBlockResponse implements CommonResponse {

    private Long listBlockId;
    private String listUUID;
    private String listLabel;
    private String listTitle;
    private String listDescription;
    private Boolean isLink;

    private ListBlockResponse(){}

    @Builder
    public ListBlockResponse(final Long listBlockId, final String listUUID, final String listLabel, final String listTitle,
                             final String listDescription, final Boolean isLink) {
        this.listBlockId = listBlockId;
        this.listUUID = listUUID;
        this.listLabel = listLabel;
        this.listTitle = listTitle;
        this.listDescription = listDescription;
        this.isLink = isLink;
    }

    public static ListBlockResponse from(ListBlock listBlock) {
        return ListBlockResponse.builder()
                .listBlockId(listBlock.getId())
                .listUUID(listBlock.getListUUID())
                .listLabel(listBlock.getListLabel())
                .listTitle(listBlock.getListTitle())
                .listDescription(listBlock.getListDescription())
                .isLink(listBlock.isLink())
                .build();

    }
}

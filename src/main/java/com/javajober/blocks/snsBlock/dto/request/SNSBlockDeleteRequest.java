package com.javajober.blocks.snsBlock.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class SNSBlockDeleteRequest {

    List<Long> snsBlockIds;

    private SNSBlockDeleteRequest() {

    }
}
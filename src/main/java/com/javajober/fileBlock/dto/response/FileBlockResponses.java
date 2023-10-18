package com.javajober.fileBlock.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FileBlockResponses {

    List<FileBlockResponse> subData;

    private FileBlockResponses() {

    }

    @Builder
    public FileBlockResponses(final List<FileBlockResponse> subData) {
        this.subData = subData;
    }
}
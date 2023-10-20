package com.javajober.blocks.fileBlock.filedto;

import lombok.Getter;

@Getter
public class FileBlockUpdateRequest {

    private Long fileBlockId;
    private String fileTitle;
    private String fileDescription;

    public FileBlockUpdateRequest() {

    }
}
package com.javajober.fileBlock.filedto;

import com.javajober.fileBlock.domain.FileBlock;
import lombok.Getter;

@Getter
public class FileBlockUpdateRequest {

    private Long fileBlockId;
    private String fileTitle;
    private String fileDescription;

    public FileBlockUpdateRequest() {

    }
}
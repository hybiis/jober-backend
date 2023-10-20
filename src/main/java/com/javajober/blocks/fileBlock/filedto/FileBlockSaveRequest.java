package com.javajober.blocks.fileBlock.filedto;

import com.javajober.blocks.fileBlock.domain.FileBlock;
import lombok.Getter;

@Getter
public class FileBlockSaveRequest {

    private String fileTitle;
    private String fileDescription;

    public FileBlockSaveRequest() {

    }

    public static FileBlock toEntity(final FileBlockSaveRequest fileBlockSaveRequest, final String fileName) {
        return FileBlock.builder()
                .fileTitle(fileBlockSaveRequest.getFileTitle())
                .fileDescription(fileBlockSaveRequest.getFileDescription())
                .fileName(fileName)
                .build();
    }
}
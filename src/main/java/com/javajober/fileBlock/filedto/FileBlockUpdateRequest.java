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

    public static FileBlock toEntity(FileBlockUpdateRequest fileBlockSaveRequest, String fileName) {
        return FileBlock.builder()
                .fileTitle(fileBlockSaveRequest.getFileTitle())
                .fileDescription(fileBlockSaveRequest.getFileDescription())
                .fileName(fileName)
                .build();
    }
}

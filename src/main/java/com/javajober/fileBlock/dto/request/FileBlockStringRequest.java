package com.javajober.fileBlock.dto.request;

import com.javajober.fileBlock.domain.FileBlock;
import lombok.Getter;

@Getter
public class FileBlockStringRequest {

    private String fileTitle;
    private String fileDescription;
    private String fileName;
    private String file;

    public FileBlockStringRequest() {
    }

    public static FileBlock toEntity(FileBlockStringRequest fileBlockStringRequest) {
        return FileBlock.builder()
                .fileTitle(fileBlockStringRequest.getFileTitle())
                .fileDescription(fileBlockStringRequest.getFileDescription())
                .fileName(fileBlockStringRequest.getFileName())
                .file(fileBlockStringRequest.getFile())
                .build();
    }
}

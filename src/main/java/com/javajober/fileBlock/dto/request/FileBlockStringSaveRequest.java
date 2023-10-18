package com.javajober.fileBlock.dto.request;

import com.javajober.fileBlock.domain.FileBlock;
import lombok.Getter;

@Getter
public class FileBlockStringSaveRequest {

    private String fileTitle;
    private String fileDescription;
    private String fileName;
    private String file;

    public FileBlockStringSaveRequest() {

    }

    public static FileBlock toEntity(final FileBlockStringSaveRequest fileBlockStringSaveRequest) {
        return FileBlock.builder()
                .fileTitle(fileBlockStringSaveRequest.getFileTitle())
                .fileDescription(fileBlockStringSaveRequest.getFileDescription())
                .fileName(fileBlockStringSaveRequest.getFileName())
                .file(fileBlockStringSaveRequest.getFile())
                .build();
    }
}
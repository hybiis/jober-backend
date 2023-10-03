package com.javajober.fileBlock.dto.response;

import com.javajober.core.util.CommonResponse;
import com.javajober.fileBlock.domain.FileBlock;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileBlockResponse implements CommonResponse {

    private Long fileBlockId;
    private String fileTitle;
    private String fileDescription;
    private String fileName;

    private FileBlockResponse() {

    }

    @Builder
    public FileBlockResponse(final Long fileBlockId, final String fileTitle, final String fileDescription, final String fileName) {
        this.fileBlockId = fileBlockId;
        this.fileTitle = fileTitle;
        this.fileDescription = fileDescription;
        this.fileName = fileName;
    }

    public static FileBlockResponse from(FileBlock fileBlock) {
        return FileBlockResponse.builder()
                .fileBlockId(fileBlock.getId())
                .fileTitle(fileBlock.getFileTitle())
                .fileDescription(fileBlock.getFileDescription())
                .fileName(fileBlock.getFileName())
                .build();
    }
}

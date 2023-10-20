package com.javajober.blocks.fileBlock.dto.request;

import com.javajober.blocks.fileBlock.domain.FileBlock;

import lombok.Getter;

@Getter
public class FileBlockStringUpdateRequest {

	private Long fileBlockId;
	private String fileTitle;
	private String fileDescription;
	private String fileName;
	private String file;

	public FileBlockStringUpdateRequest() {

	}

	public static FileBlock toEntity(final FileBlockStringUpdateRequest fileBlockSaveRequest) {
		return FileBlock.builder()
			.fileTitle(fileBlockSaveRequest.getFileTitle())
			.fileDescription(fileBlockSaveRequest.getFileDescription())
			.fileName(fileBlockSaveRequest.getFile())
			.file(fileBlockSaveRequest.getFile())
			.build();
	}
}
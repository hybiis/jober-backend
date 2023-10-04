package com.javajober.fileBlock.dto.request;

import lombok.Getter;

@Getter
public class FileBlockStringUpdateRequest {
	private Long fileBlockId;
	private String fileTitle;
	private String fileDescription;
	private String fileName;

	public FileBlockStringUpdateRequest(){

	}
}

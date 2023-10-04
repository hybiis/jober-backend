package com.javajober.fileBlock.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class FileBlockStringUpdateRequests {
	List<FileBlockStringUpdateRequest> subData;

	private FileBlockStringUpdateRequests() {
	}

}
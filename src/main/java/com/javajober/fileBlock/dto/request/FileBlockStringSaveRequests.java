package com.javajober.fileBlock.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class FileBlockStringSaveRequests {

    List<FileBlockStringSaveRequest> subData;

    private FileBlockStringSaveRequests() {

    }

    public FileBlockStringSaveRequests(final List<FileBlockStringSaveRequest> subData) {

        this.subData = subData;
    }
}
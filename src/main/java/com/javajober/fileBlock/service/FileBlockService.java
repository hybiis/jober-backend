package com.javajober.fileBlock.service;

import com.javajober.core.config.FileDirectoryConfig;
import com.javajober.core.error.exception.Exception500;
import com.javajober.core.message.ErrorMessage;
import com.javajober.fileBlock.domain.FileBlock;
import com.javajober.fileBlock.filedto.FileBlockSaveRequest;
import com.javajober.fileBlock.filedto.FileBlockSaveRequests;
import com.javajober.fileBlock.filedto.FileBlockUpdateRequest;
import com.javajober.fileBlock.filedto.FileBlockUpdateRequests;
import com.javajober.fileBlock.dto.response.FileBlockResponse;
import com.javajober.fileBlock.dto.response.FileBlockResponses;
import com.javajober.fileBlock.repository.FileBlockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileBlockService {

    private final FileBlockRepository fileBlockRepository;
    private final FileDirectoryConfig fileDirectoryConfig;

    public FileBlockService(FileBlockRepository fileBlockRepository, FileDirectoryConfig fileDirectoryConfig) {
        this.fileBlockRepository = fileBlockRepository;
        this.fileDirectoryConfig = fileDirectoryConfig;
    }

    @Transactional
    public void save(FileBlockSaveRequests saveRequests, MultipartFile file) {

        String fileName = uploadFile(file);

        List<FileBlockSaveRequest> subData = saveRequests.getSubData();
        for (FileBlockSaveRequest saveRequest : subData) {
            FileBlock fileBlock = FileBlockSaveRequest.toEntity(saveRequest, fileName);
            fileBlockRepository.save(fileBlock);
        }
    }

    public FileBlockResponses find(List<Long> fileIds) {

        List<FileBlockResponse> fileBlockResponses = new ArrayList<>();
        for (Long fileId : fileIds) {
            FileBlock fileBlock = fileBlockRepository.findFileBlock(fileId);
            FileBlockResponse response = FileBlockResponse.from(fileBlock);
            fileBlockResponses.add(response);
        }

        return new FileBlockResponses(fileBlockResponses);
    }

    @Transactional
    public void update(FileBlockUpdateRequests updateRequests, MultipartFile file) {

        List<FileBlockUpdateRequest> subData = updateRequests.getSubData();

        for (FileBlockUpdateRequest updateRequest : subData) {

            FileBlock fileBlockPS = fileBlockRepository.findFileBlock(updateRequest.getFileBlockId());
            String fileNamePS = fileBlockPS.getFileName();
            deleteFile(fileNamePS);

            String fileName = uploadFile(file);
            fileBlockPS.fileUpdate(updateRequest.getFileTitle(), updateRequest.getFileDescription(), fileName);

            fileBlockRepository.save(fileBlockPS);
        }
    }

    private void deleteFile(String fileName) {

        File directory = new File(getDirectoryPath() + fileName);
        if (directory.exists()) {
            boolean isDeleted = directory.delete();
            if (!isDeleted) {
                throw new Exception500(ErrorMessage.FAILED_DELETE_FILE);
            }
        }
    }

    private String uploadFile(MultipartFile file) {

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // 테스트용
        String fileUploadPth = getDirectoryPath() + fileName;

        try {
            file.transferTo(new File(fileUploadPth));
        } catch (IOException e) {
            throw new Exception500(ErrorMessage.FILE_UPLOAD_FAILED);
        }

        return fileName;
    }

    private String getDirectoryPath() {
        return fileDirectoryConfig.getDirectoryPath();
    }
}

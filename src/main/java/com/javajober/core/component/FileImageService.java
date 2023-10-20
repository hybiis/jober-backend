package com.javajober.core.component;

import com.javajober.core.config.FileDirectoryConfig;
import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class FileImageService {

    private final FileDirectoryConfig fileDirectoryConfig;

    public FileImageService(final FileDirectoryConfig fileDirectoryConfig) {
        this.fileDirectoryConfig = fileDirectoryConfig;
    }

    public String uploadFile(final MultipartFile file) {

        if (file.isEmpty()) {
            return null;
        }
        if (file.getOriginalFilename() == null) {
            throw new ApplicationException(ApiStatus.NOT_FOUND, "이름이 없는 파일입니다.");
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String fileUploadPath = getDirectoryPath() + fileName;

        try {
            file.transferTo(new File(fileUploadPath));
        } catch (IOException e) {
            throw new ApplicationException(ApiStatus.IO_EXCEPTION, "파일 업로드 중 실패하였습니다.");
        }
        return fileName;
    }

    private String getDirectoryPath() {
        return fileDirectoryConfig.getDirectoryPath();
    }

    public void validatePdfFile(final List<MultipartFile> files) {

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                throw new ApplicationException(ApiStatus.INVALID_DATA, "업로드할 파일이 없습니다.");
            }
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new ApplicationException(ApiStatus.NOT_FOUND, "이름이 없는 파일입니다.");
            }
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex < 0 || !(originalFilename.substring(dotIndex + 1).equalsIgnoreCase("pdf"))) {
                throw new ApplicationException(ApiStatus.INVALID_DATA, "첨부 파일은 pdf만 가능합니다.");
            }
        }
    }
}
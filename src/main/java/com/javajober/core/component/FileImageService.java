package com.javajober.core.component;

import com.javajober.core.config.FileDirectoryConfig;
import com.javajober.core.error.exception.Exception404;
import com.javajober.core.error.exception.Exception500;
import com.javajober.core.message.ErrorMessage;
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
            throw new Exception404(ErrorMessage.INVALID_FILE_NAME);
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String fileUploadPath = getDirectoryPath() + fileName;

        try {
            file.transferTo(new File(fileUploadPath));
        } catch (IOException e) {
            throw new Exception500(ErrorMessage.FILE_UPLOAD_FAILED);
        }
        return fileName;
    }

    private String getDirectoryPath() {
        return fileDirectoryConfig.getDirectoryPath();
    }

    public void validatePdfFile(final List<MultipartFile> files) {

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                throw new Exception404(ErrorMessage.FILE_IS_EMPTY);
            }
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new Exception404(ErrorMessage.INVALID_FILE_NAME);
            }
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex < 0 || !(originalFilename.substring(dotIndex + 1).equalsIgnoreCase("pdf"))) {
                throw new Exception404(ErrorMessage.INVALID_FILE_TYPE);
            }
        }
    }
}
package com.javajober.fileBlock.controller;

import com.javajober.core.error.exception.Exception404;
import com.javajober.core.message.ErrorMessage;
import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.fileBlock.filedto.FileBlockSaveRequests;
import com.javajober.fileBlock.filedto.FileBlockUpdateRequests;
import com.javajober.fileBlock.dto.response.FileBlockResponse;
import com.javajober.fileBlock.dto.response.FileBlockResponses;
import com.javajober.fileBlock.service.FileBlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileBlockController {

    private final FileBlockService fileBlockService;

    public FileBlockController(final FileBlockService fileBlockService) {
        this.fileBlockService = fileBlockService;
    }

    @PostMapping("/fileBlock")
    public ResponseEntity<ApiUtils.ApiResponse<FileBlockResponse>> save(
            @RequestPart(value = "fileName", required = false) final MultipartFile file,
            @RequestPart(value = "fileBlockRequest") final FileBlockSaveRequests saveRequests)  {

        validationMultipartFile(file);
        fileBlockService.save(saveRequests, file);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.CREATED, SuccessMessage.CREATE_SUCCESS, null));
    }

    @GetMapping("/fileBlock")
    public ResponseEntity<ApiUtils.ApiResponse<FileBlockResponses>> find(@RequestParam final List<Long> fileIds) {

        FileBlockResponses responses = fileBlockService.find(fileIds);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.READ_SUCCESS, responses));
    }

    @PutMapping("/fileBlock")
    public ResponseEntity<ApiUtils.ApiResponse<FileBlockResponse>> update(
            @RequestPart(value = "fileName", required = false) final MultipartFile file,
            @RequestPart(value = "data") final FileBlockUpdateRequests updateRequests){

        validationMultipartFile(file);
        fileBlockService.update(updateRequests, file);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.UPDATE_SUCCESS, null));
    }

    private void validationMultipartFile(final MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new Exception404(ErrorMessage.FILE_IS_EMPTY);
        }

        String contentType = file.getContentType();
        if (!"application/pdf".equals(contentType)) {
            throw new Exception404(ErrorMessage.INVALID_FILE_TYPE);
        }
    }
}
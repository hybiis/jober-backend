package com.javajober.spaceWall.controller;

import com.javajober.core.util.file.FileImageService;
import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.filedto.SpaceWallSaveRequest;
import com.javajober.spaceWall.dto.response.SpaceWallSaveResponse;
import com.javajober.spaceWall.filedto.SpaceWallUpdateRequest;
import com.javajober.spaceWall.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api")
@Controller
public class FileUploadController {

    private final FileImageService fileImageService;
    private final FileUploadService fileUploadService;

    public FileUploadController(final FileImageService fileImageService, final FileUploadService fileUploadService) {
        this.fileImageService = fileImageService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping(path = "/wall/file", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> save(@RequestPart(value = "data") final SpaceWallSaveRequest spaceWallSaveRequest,
                                                                            @RequestPart(value = "fileName") List<MultipartFile> files,
                                                                            @RequestPart(value = "backgroundImgURL", required = false) MultipartFile backgroundImgURL,
                                                                            @RequestPart(value = "wallInfoImgURL", required = false) MultipartFile wallInfoImgURL,
                                                                            @RequestPart(value = "styleImgURL", required = false) MultipartFile styleImgURL) {

        fileImageService.validatePdfFile(files);
        SpaceWallSaveResponse data = fileUploadService.save(spaceWallSaveRequest, FlagType.SAVED, files, backgroundImgURL, wallInfoImgURL, styleImgURL);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_SAVE_SUCCESS, data));
    }

    @PutMapping(path = "/wall/file", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> update(@RequestPart(value = "data") final SpaceWallUpdateRequest spaceWallRequest,
        @RequestPart(value = "fileName") List<MultipartFile> files,
        @RequestPart(value = "backgroundImgURL", required = false) MultipartFile backgroundImgURL,
        @RequestPart(value = "wallInfoImgURL", required = false) MultipartFile wallInfoImgURL,
        @RequestPart(value = "styleImgURL", required = false) MultipartFile styleImgURL){

        SpaceWallSaveResponse data = fileUploadService.update(spaceWallRequest, FlagType.SAVED, files, backgroundImgURL, wallInfoImgURL, styleImgURL);

        return  ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, data));
    }
}

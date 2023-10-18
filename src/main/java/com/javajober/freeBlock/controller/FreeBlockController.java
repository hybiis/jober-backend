package com.javajober.freeBlock.controller;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.freeBlock.dto.request.FreeBlockSaveRequests;
import com.javajober.freeBlock.dto.request.FreeBlockUpdateRequests;
import com.javajober.freeBlock.dto.response.FreeBlockResponses;
import com.javajober.freeBlock.service.FreeBlockService;
import com.javajober.freeBlock.dto.response.FreeBlockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FreeBlockController {

    private final FreeBlockService freeBlockService;

    public FreeBlockController(final FreeBlockService freeBlockService) {
        this.freeBlockService = freeBlockService;
    }

    @PostMapping("/freeBlock")
    public ResponseEntity<ApiUtils.ApiResponse<FreeBlockResponse>> save(@RequestBody final FreeBlockSaveRequests saveRequests) {

        freeBlockService.save(saveRequests);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.CREATED, SuccessMessage.CREATE_SUCCESS, null));
    }

    @GetMapping("/freeBlock")
    public ResponseEntity<ApiUtils.ApiResponse<FreeBlockResponses>> find(@RequestParam final List<Long> freeIds) {

        FreeBlockResponses data = freeBlockService.find(freeIds);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.READ_SUCCESS, data));
    }

    @PutMapping("/freeBlock")
    public ResponseEntity<ApiUtils.ApiResponse<FreeBlockResponse>> update(@RequestBody final FreeBlockUpdateRequests updateRequests) {

        freeBlockService.update(updateRequests);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.UPDATE_SUCCESS, null));
    }
}
package com.javajober.spaceWall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.dto.request.DeleteTemporaryRequest;
import com.javajober.spaceWall.dto.request.SpaceWallRequest;
import com.javajober.spaceWall.dto.request.SpaceWallUpdateRequest;
import com.javajober.spaceWall.dto.response.SpaceWallResponse;
import com.javajober.spaceWall.dto.response.SpaceWallSaveResponse;
import com.javajober.spaceWall.dto.response.SpaceWallTemporaryResponse;
import com.javajober.spaceWall.service.SpaceWallFindService;
import com.javajober.spaceWall.service.SpaceWallService;
import com.javajober.spaceWall.service.SpaceWallTemporaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
public class SpaceWallController {

    private final SpaceWallService spaceWallService;
    private final SpaceWallFindService spaceWallFindService;
    private final SpaceWallTemporaryService spaceWallTemporaryService;

    public SpaceWallController(final SpaceWallService spaceWallService, final SpaceWallFindService spaceWallFindService, final SpaceWallTemporaryService spaceWallTemporaryService) {
        this.spaceWallService = spaceWallService;
        this.spaceWallFindService = spaceWallFindService;
        this.spaceWallTemporaryService = spaceWallTemporaryService;
    }

    @GetMapping("/wall-temporary/storage/{memberId}/{addSpaceId}")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallTemporaryResponse>> checkSpaceWallTemporary(@PathVariable Long memberId, @PathVariable Long addSpaceId) {

        SpaceWallTemporaryResponse response = spaceWallTemporaryService.checkSpaceWallTemporary(memberId, addSpaceId);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_READ_SUCCESS, response));
    }

    @PostMapping("/wall")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> save(@RequestBody final SpaceWallRequest spaceWallRequest) {

        SpaceWallSaveResponse response = spaceWallService.save(spaceWallRequest, FlagType.SAVED);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_SAVE_SUCCESS, response));
    }

    @PostMapping(path = "/wall-temporary")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> savePending (@RequestBody final SpaceWallRequest spaceWallRequest) {

       SpaceWallSaveResponse response = spaceWallService.save(spaceWallRequest, FlagType.PENDING);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_SAVE_SUCCESS, response));
    }

    @PutMapping("/wall")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> update(@RequestBody final SpaceWallUpdateRequest spaceWallUpdateRequest){

        SpaceWallSaveResponse response = spaceWallService.update(spaceWallUpdateRequest, FlagType.SAVED);

        return  ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, response));
    }

    @GetMapping("/wall/{memberId}/{addSpaceId}/{spaceWallId}")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallResponse>> findSaved (
            @PathVariable Long memberId, @PathVariable Long addSpaceId, @PathVariable Long spaceWallId) throws JsonProcessingException {

        SpaceWallResponse data = spaceWallFindService.find(memberId, addSpaceId, spaceWallId, FlagType.SAVED);
        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_READ_SUCCESS, data));
    }

    @GetMapping("/wall-temporary/{memberId}/{addSpaceId}/{spaceWallId}")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallResponse>> findPending(
            @PathVariable Long memberId, @PathVariable Long addSpaceId, @PathVariable Long spaceWallId) throws JsonProcessingException {

        SpaceWallResponse data = spaceWallFindService.find(memberId, addSpaceId, spaceWallId, FlagType.PENDING);
        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_READ_SUCCESS, data));
    }

    @PutMapping("/wall-temporary")
    public ResponseEntity<ApiUtils.ApiResponse> deleteTemporary(@RequestBody final DeleteTemporaryRequest deleteTemporaryRequest) {

        Long memberId = deleteTemporaryRequest.getMemberId();
        Long addSpaceId = deleteTemporaryRequest.getAddSpaceId();

        spaceWallTemporaryService.deleteTemporary(memberId, addSpaceId);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_DELETE_SUCCESS, null));
    }
}

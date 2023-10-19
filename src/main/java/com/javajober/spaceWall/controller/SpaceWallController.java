package com.javajober.spaceWall.controller;

import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiResponse;
import com.javajober.core.util.ApiUtils;
import com.javajober.exception.ApiStatus;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.dto.request.TemporaryDeleteRequest;
import com.javajober.spaceWall.dto.request.SpaceWallStringRequest;
import com.javajober.spaceWall.dto.request.SpaceWallStringUpdateRequest;
import com.javajober.spaceWall.dto.response.DuplicateURLResponse;
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

    @PostMapping("/wall")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> save(
            @RequestBody final SpaceWallStringRequest spaceWallRequest) {

        SpaceWallSaveResponse data = spaceWallService.save(spaceWallRequest, FlagType.SAVED);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_SAVE_SUCCESS, data));
    }

    @PostMapping(path = "/wall-temporary")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> savePending (
            @RequestBody final SpaceWallStringRequest spaceWallRequest) {

       SpaceWallSaveResponse data = spaceWallService.save(spaceWallRequest, FlagType.PENDING);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_SAVE_SUCCESS, data));
    }

    @GetMapping("/wall-temporary/storage/{memberId}/{addSpaceId}")
    public ResponseEntity<ApiResponse.Response<SpaceWallTemporaryResponse>> hasSpaceWallTemporary(
            @PathVariable final Long memberId, @PathVariable final Long addSpaceId) {

        SpaceWallTemporaryResponse data = spaceWallTemporaryService.hasSpaceWallTemporary(memberId, addSpaceId);

        return ApiResponse.response(ApiStatus.OK, "공유페이지 임시 저장 조회를 성공했습니다.", data);
    }

    @GetMapping("/wall/{memberId}/{addSpaceId}/{spaceWallId}")
    public ResponseEntity<ApiResponse.Response<SpaceWallResponse>> find (
            @PathVariable final Long memberId, @PathVariable final Long addSpaceId, @PathVariable final Long spaceWallId){

        SpaceWallResponse data = spaceWallFindService.find(memberId, addSpaceId, spaceWallId, FlagType.SAVED);

        return ApiResponse.response(ApiStatus.OK, "공유페이지 조회를 성공했습니다.", data);
    }

    @GetMapping("/wall-temporary/{memberId}/{addSpaceId}/{spaceWallId}")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallResponse>> findPending(
            @PathVariable final Long memberId, @PathVariable final Long addSpaceId, @PathVariable final Long spaceWallId){

        SpaceWallResponse data = spaceWallFindService.find(memberId, addSpaceId, spaceWallId, FlagType.PENDING);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_READ_SUCCESS, data));
    }

    @GetMapping("/wall/shareURL/{shareURL}")
    public ResponseEntity<ApiResponse.Response<SpaceWallResponse>> findByShareURL(@PathVariable final String shareURL) {

        SpaceWallResponse data = spaceWallFindService.findByShareURL(shareURL);

        return ApiResponse.response(ApiStatus.OK, "공유페이지 조회를 성공했습니다.", data);
    }

    @GetMapping("/wall/has-duplicate/{shareURL}")
    public ResponseEntity<ApiUtils.ApiResponse<DuplicateURLResponse>> hasDuplicateShareURL (@PathVariable final String shareURL) {

        DuplicateURLResponse data = spaceWallFindService.hasDuplicateShareURL(shareURL);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SUCCESS, data));
    }

    @PutMapping("/wall")
    public ResponseEntity<ApiUtils.ApiResponse<SpaceWallSaveResponse>> update(@RequestBody final SpaceWallStringUpdateRequest spaceWallUpdateRequest){

        SpaceWallSaveResponse data = spaceWallService.update(spaceWallUpdateRequest, FlagType.SAVED);

        return  ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, data));
    }

    @PutMapping("/wall-temporary")
    public ResponseEntity<ApiUtils.ApiResponse> deleteTemporary(@RequestBody final TemporaryDeleteRequest temporaryDeleteRequest) {

        spaceWallTemporaryService.delete(temporaryDeleteRequest.getMemberId(), temporaryDeleteRequest.getSpaceId());

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_DELETE_SUCCESS, null));
    }
}
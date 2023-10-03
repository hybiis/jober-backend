package com.javajober.spaceWall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.spaceWall.domain.FlagType;
import com.javajober.spaceWall.dto.request.DeleteTemporaryRequest;
import com.javajober.spaceWall.dto.request.SpaceWallRequest;
import com.javajober.spaceWall.dto.request.SpaceWallUpdateRequest;
import com.javajober.spaceWall.dto.response.SpaceWallResponse;
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

        SpaceWallTemporaryResponse response = spaceWallService.checkSpaceWallTemporary(memberId, addSpaceId);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.SPACE_WALL_TEMPORARY_READ_SUCCESS, response));
    }

    @PostMapping("/wall")
    public ResponseEntity<?> save(@RequestBody final SpaceWallRequest spaceWallRequest) {

        spaceWallService.save(spaceWallRequest, FlagType.SAVED);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, null));
    }

    @PostMapping(path = "/wall-temporary")
    public ResponseEntity<?> savePending (@RequestBody final SpaceWallRequest spaceWallRequest)
    {

        spaceWallService.save(spaceWallRequest, FlagType.PENDING);

        return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, null));
    }

    @PutMapping("/wall")
    public ResponseEntity<?> update(@RequestBody final SpaceWallUpdateRequest spaceWallUpdateRequest){

        spaceWallService.update(spaceWallUpdateRequest, FlagType.SAVED);

        return  ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, SuccessMessage.CREATE_SUCCESS, null));
    }
}

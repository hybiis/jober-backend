package com.javajober.space.controller;

import com.javajober.core.util.ApiResponse;
import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.space.service.SpaceService;
import com.javajober.space.dto.response.SpaceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/employee")
@RestController
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(final SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/{memberId}/{addSpaceId}")
    public ResponseEntity<ApiResponse.Response<SpaceResponse>> find(
            @PathVariable final Long memberId, @PathVariable final Long addSpaceId, @RequestParam final String spaceType) {

        if (spaceType.isEmpty()) {
            throw new ApplicationException(ApiStatus.OBJECT_EMPTY, "spaceType 값이 요청되지 않았습니다.");
        }
        SpaceResponse data = spaceService.find(memberId, addSpaceId, spaceType);

        return ApiResponse.response(ApiStatus.OK, "스페이스 조회를 성공했습니다.", data);
    }
}
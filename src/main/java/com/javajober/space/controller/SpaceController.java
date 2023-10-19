package com.javajober.space.controller;

import com.javajober.core.util.ApiResponse;
import com.javajober.exception.ApiStatus;
import com.javajober.exception.ApplicationException;
import com.javajober.security.JwtTokenizer;
import com.javajober.space.domain.SpaceType;
import com.javajober.space.dto.request.SpaceSaveRequest;
import com.javajober.space.dto.response.SpaceResponse;
import com.javajober.space.dto.response.SpaceSaveResponse;
import com.javajober.space.service.SpaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class SpaceController {

    private final SpaceService spaceService;
    private final JwtTokenizer jwtTokenizer;

    public SpaceController(final SpaceService spaceService, final JwtTokenizer jwtTokenizer) {
        this.spaceService = spaceService;
        this.jwtTokenizer = jwtTokenizer;
    }

    @PostMapping("/space")
    public ResponseEntity<ApiResponse.Response<SpaceSaveResponse>> save(
            @RequestBody final SpaceSaveRequest request, @RequestHeader("Authorization") String token){

        if (!request.getSpaceType().equals(SpaceType.ORGANIZATION.getEngTitle())){
            throw new ApplicationException(ApiStatus.INVALID_DATA, "스페이스 생성은 organization만 가능");
        }
        Long memberId = jwtTokenizer.getUserIdFromToken(token);
        SpaceSaveResponse data = spaceService.save(request, memberId);

        return ApiResponse.response(ApiStatus.OK, "스페이스 저장을 성공했습니다.", data);
    }

    @GetMapping("/employee/{addSpaceId}")
    public ResponseEntity<ApiResponse.Response<SpaceResponse>> find(
            @PathVariable final Long addSpaceId, @RequestParam final String spaceType, @RequestHeader("Authorization") String token) {

        if (spaceType.isEmpty()) {
            throw new ApplicationException(ApiStatus.OBJECT_EMPTY, "spaceType 값이 요청되지 않았습니다.");
        }

        Long memberId = jwtTokenizer.getUserIdFromToken(token);
        SpaceResponse data = spaceService.find(addSpaceId, spaceType, memberId);

        return ApiResponse.response(ApiStatus.OK, "스페이스 조회를 성공했습니다.", data);
    }
}
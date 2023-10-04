package com.javajober.space.controller;

import com.javajober.core.message.ErrorMessage;
import com.javajober.core.message.SuccessMessage;
import com.javajober.core.util.ApiUtils;
import com.javajober.space.domain.SpaceType;
import com.javajober.space.service.SpaceService;
import com.javajober.space.dto.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class SpaceController {
    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping("/{memberId}/{addSpaceId}")
    public ResponseEntity<ApiUtils.ApiResponse<DataResponse>> getEmployeeData(@PathVariable Long memberId, @PathVariable Long addSpaceId, @RequestParam String spaceType) {
        try {
            DataResponse data = spaceService.getEmployeeData(memberId, addSpaceId, spaceType);

            SuccessMessage successMessage;
            if (SpaceType.PERSONAL.name().equalsIgnoreCase(spaceType)) {
                successMessage = SuccessMessage.PERSONAL_SPACE_QUERY_SUCCESS;
            } else if (SpaceType.ORGANIZATION.name().equalsIgnoreCase(spaceType)) {
                successMessage = SuccessMessage.ORGANIZATION_SPACE_QUERY_SUCCESS;
            } else {
                throw new IllegalArgumentException(ErrorMessage.INVALID_SPACE_TYPE.getMessage());
            }
            return ResponseEntity.ok(ApiUtils.success(HttpStatus.OK, successMessage, data));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiUtils.ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
        }
    }

}

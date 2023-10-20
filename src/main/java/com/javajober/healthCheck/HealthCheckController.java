package com.javajober.healthCheck;

import com.javajober.core.exception.ApiStatus;
import com.javajober.core.util.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/healthCheck")
    public ResponseEntity<ApiResponse.Response<Boolean>> healthCheck() {

        Boolean data = true;

        return ApiResponse.response(ApiStatus.OK, "헬스 체크를 성공했습니다.", data);
    }
}
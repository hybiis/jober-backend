package com.javajober.core.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import com.javajober.core.message.SuccessMessage;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response){
        return new ApiResult<>(true, response, null);
    }

    public static <T> ApiResponse<T> success(HttpStatus status, SuccessMessage message, T response){
        return new ApiResponse<>(true, status.value(), message.getMessage(), response);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(message, status.value()));
    }


    @Getter
    public static class ApiResult<T> {
        private final boolean success;
        private final T data;
        private final ApiError error;

        public ApiResult(boolean success, T data, ApiError error) {
            this.success = success;
            this.data = data;
            this.error = error;
        }
    }

    @Getter
    public static class ApiResponse<T>{
        private final boolean success;
        private final int code;
        private final String message;
        private final T data;

        public ApiResponse(boolean success, int code, String message, T data) {
            this.success = success;
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }

    @Getter
    public static class ApiError {
        private final String message;
        private final int status;

        public ApiError(String message, int status) {
            this.message = message;
            this.status = status;
        }
    }
}
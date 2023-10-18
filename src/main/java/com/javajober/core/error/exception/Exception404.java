package com.javajober.core.error.exception;

import com.javajober.core.message.ErrorMessage;
import com.javajober.core.util.ApiUtils;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class Exception404 extends RuntimeException {

    public Exception404(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public ApiUtils.ApiResult<?> body() {
        return ApiUtils.error(getMessage(), HttpStatus.NOT_FOUND);
    }

    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}

package com.javajober.core.error.exception;

import com.javajober.core.message.ErrorMessage;
import com.javajober.core.util.ApiUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Exception500 extends RuntimeException {
    public Exception500(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public ApiUtils.ApiResult<?> body() {
        return ApiUtils.error(getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}

package com.javajober.core.error.exception;

import com.javajober.core.message.ErrorMessage;
import com.javajober.core.util.ApiUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Exception400 extends RuntimeException {
    private String key;
    private String value;
    private String message;

    public Exception400(String key, String value) {
        super(key+" : "+value);
        this.key = key;
        this.value = value;
    }

    public Exception400(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public ApiUtils.ApiResult<?> body(){
        return ApiUtils.error(getMessage(), HttpStatus.BAD_REQUEST);
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}

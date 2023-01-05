package com.api.codeplatform.exceptions;

import org.springframework.http.HttpStatus;

public class ApiExceptionResponse {
    private String message;
    private HttpStatus httpStatus;

    public ApiExceptionResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

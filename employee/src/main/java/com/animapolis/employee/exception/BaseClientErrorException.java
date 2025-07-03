package com.animapolis.employee.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseClientErrorException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BaseClientErrorException(String message) {
        super(message);
    }

    public BaseClientErrorException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}

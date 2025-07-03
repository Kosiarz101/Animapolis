package com.animapolis.employee.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseClientErrorException {

    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

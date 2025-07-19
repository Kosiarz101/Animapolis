package org.animapolis.healthcare.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseClientErrorException {

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

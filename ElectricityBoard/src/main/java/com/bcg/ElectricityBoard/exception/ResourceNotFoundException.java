package com.bcg.ElectricityBoard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    //Custom Exception created in superclass
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
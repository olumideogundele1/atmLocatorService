package com.lbg.open_banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomInternalServerError extends RuntimeException{

    public CustomInternalServerError(String message) {
        super(message);
    }
}

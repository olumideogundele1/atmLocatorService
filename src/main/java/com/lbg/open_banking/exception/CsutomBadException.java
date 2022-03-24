package com.lbg.open_banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CsutomBadException extends RuntimeException{
    public CsutomBadException(String message){
        super(message);
    }
}

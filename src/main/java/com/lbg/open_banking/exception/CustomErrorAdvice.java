package com.lbg.open_banking.exception;

import com.lbg.open_banking.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class CustomErrorAdvice  extends ResponseEntityExceptionHandler {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomErrorAdvice.class);

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({CustomNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(CustomNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler({CsutomBadException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNPException(CustomNotFoundException e) {
        return error(BAD_REQUEST, e);
    }

    @ExceptionHandler({CustomInternalServerError.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInternalServerException(CustomInternalServerError e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({NumberFormatException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNFEException(NumberFormatException e) {
        return error(BAD_REQUEST, e);
    }


    private ResponseEntity<ErrorResponse> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), e.getMessage(), false));
    }



}




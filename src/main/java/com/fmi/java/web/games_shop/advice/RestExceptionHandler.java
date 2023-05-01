package com.fmi.java.web.games_shop.advice;

import com.fmi.java.web.games_shop.dto.Error;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(
            final RuntimeException ex, final WebRequest request) {
        final String message = ex.getMessage();
        final Error error = new Error(message);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}

package com.meltrevelyan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Collections;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("Required entity was not found");
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        String stackTrace = out.toString();
        return new ApiError(HttpStatus.NOT_FOUND, "Required entity was not found", e.getMessage(),
                Collections.singletonList(stackTrace), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleUnknownException(Throwable e) {
        log.error("Unexpected error has occurred", e);
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        String stackTrace = out.toString();
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", e.getMessage(),
                Collections.singletonList(stackTrace), LocalDateTime.now());
    }
}

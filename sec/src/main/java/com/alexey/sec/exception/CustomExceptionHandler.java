package com.alexey.sec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequestException(BadRequestException e) {
        return new ErrorDto(e.getMessage(), "400");
    }
    @ExceptionHandler(ForbiddenRequestException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDto handleForbiddenException(ForbiddenRequestException e) {
        return new ErrorDto(e.getMessage(), "403");
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleForbiddenException(NotFoundException e) {
        return new ErrorDto(e.getMessage(), "404");
    }
}
package com.amsidh.mvc.controller;

import com.amsidh.mvc.exception.ToDoNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ToDoControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ToDoNotFoundException.class})
    public ResponseEntity<Object> notFoundException(ToDoNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getExceptionModel(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}

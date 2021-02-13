package com.amsidh.mvc.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoNotFoundException extends RuntimeException {
    private ExceptionModel exceptionModel;
}

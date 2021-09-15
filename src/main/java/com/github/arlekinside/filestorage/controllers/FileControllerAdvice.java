package com.github.arlekinside.filestorage.controllers;

import com.github.arlekinside.filestorage.models.responses.FileProcessingErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


@RestControllerAdvice
public class FileControllerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public FileProcessingErrorResponse handleException(BindException ex) {
        return new FileProcessingErrorResponse(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
    }
}
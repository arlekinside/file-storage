package com.github.arlekinside.filestorage.controllers;

import com.github.arlekinside.filestorage.exceptions.FileNotFoundException;
import com.github.arlekinside.filestorage.exceptions.TagsException;
import com.github.arlekinside.filestorage.models.responses.FileProcessingErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * Handles custom API calls exceptions
 */
@RestControllerAdvice
public class FileControllerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    FileProcessingErrorResponse handleException(BindException ex) {
        return new FileProcessingErrorResponse(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    FileProcessingErrorResponse handleException(FileNotFoundException ex){
        return new FileProcessingErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(TagsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    FileProcessingErrorResponse handleException(TagsException ex){
        return new FileProcessingErrorResponse(ex.getMessage());
    }
}

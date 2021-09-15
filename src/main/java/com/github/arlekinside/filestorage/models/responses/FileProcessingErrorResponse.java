package com.github.arlekinside.filestorage.models.responses;

/**
 * File saving validation exception response <br>
 * "Success" field's default value = false
 */
public class FileProcessingErrorResponse {

    private boolean success = false;
    private String error;

    public FileProcessingErrorResponse(String error) {
        this.error = error;
    }

    public FileProcessingErrorResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }
}

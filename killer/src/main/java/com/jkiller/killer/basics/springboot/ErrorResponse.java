package com.jkiller.killer.basics.springboot;

public class ErrorResponse {

    private String message;
    private String errorTypeName;

    public ErrorResponse(String errorTypeName, String message) {
        this.errorTypeName = errorTypeName;
        this.message = message;
    }

    public ErrorResponse(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorTypeName() {
        return errorTypeName;
    }

    public void setErrorTypeName(String errorTypeName) {
        this.errorTypeName = errorTypeName;
    }
}

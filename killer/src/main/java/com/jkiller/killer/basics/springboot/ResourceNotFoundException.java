package com.jkiller.killer.basics.springboot;

/**
 * 自定义异常类型
 */
public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.nhnacademy.springboot.taskapi.exception;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super("Permission denied: You cannot create projects.");
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }
}

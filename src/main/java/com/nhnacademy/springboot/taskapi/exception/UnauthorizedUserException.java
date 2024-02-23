package com.nhnacademy.springboot.taskapi.exception;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super("Permission denied: Login required");
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
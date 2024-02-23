package com.nhnacademy.springboot.taskapi.exception;

public class ProjectAlreadyExistsException extends RuntimeException{
    public ProjectAlreadyExistsException() {
        super("Project already exists.");
    }

    public ProjectAlreadyExistsException(String message) {
        super(message);
    }
}

package com.nhnacademy.springboot.taskapi.exception;

public class ProjectMemberAlreadyExistsException extends RuntimeException{
    public ProjectMemberAlreadyExistsException() {
        super("Member already exists in this project.");
    }

    public ProjectMemberAlreadyExistsException(String message) {
        super(message);
    }
}

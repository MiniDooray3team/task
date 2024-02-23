package com.nhnacademy.springboot.taskapi.exception;

public class ProjectMemberNotFoundException extends RuntimeException {
    public ProjectMemberNotFoundException() {
        super("User is not a member of the project.");
    }

    public ProjectMemberNotFoundException(String message) {
        super(message);
    }
}

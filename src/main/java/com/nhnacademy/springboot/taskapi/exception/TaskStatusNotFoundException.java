package com.nhnacademy.springboot.taskapi.exception;

public class TaskStatusNotFoundException extends RuntimeException{
    public TaskStatusNotFoundException() {
        super("TaskStatus not found.");
    }

    public TaskStatusNotFoundException(String message) {
        super(message);
    }
}

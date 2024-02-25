package com.nhnacademy.springboot.taskapi.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException() {
        super("Comment not found.");
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}

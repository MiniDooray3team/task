package com.nhnacademy.springboot.taskapi.exception;

public class MileStoneAlreadyExistsException extends RuntimeException{
    public MileStoneAlreadyExistsException() {
        super("MileStone already exists.");
    }

    public MileStoneAlreadyExistsException(String message) {
        super(message);
    }
}

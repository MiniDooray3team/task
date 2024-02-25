package com.nhnacademy.springboot.taskapi.exception;

public class MileStoneNotFoundException extends RuntimeException {
    public MileStoneNotFoundException() {
        super("MileStone not found.");
    }

    public MileStoneNotFoundException(String message) {
        super(message);
    }
}

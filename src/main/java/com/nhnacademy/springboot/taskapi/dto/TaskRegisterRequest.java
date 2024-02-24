package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRegisterRequest {
    private String name;

    public TaskRegisterRequest(String name) {
        this.name = name;
    }
}

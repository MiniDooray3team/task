package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskRegisterRequest {
    private String name;

    public TaskRegisterRequest(String name) {
        this.name = name;
    }
}

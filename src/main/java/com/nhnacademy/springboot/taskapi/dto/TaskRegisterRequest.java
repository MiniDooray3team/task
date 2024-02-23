package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRegisterRequest {
    private String name;
    private Long projectId;

    public TaskRegisterRequest(String name, Long projectId) {
        this.name = name;
        this.projectId = projectId;
    }
}

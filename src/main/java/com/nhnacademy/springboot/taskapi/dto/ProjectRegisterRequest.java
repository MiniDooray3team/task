package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRegisterRequest {
    private String name;
    private Long projectStatusId;

    public ProjectRegisterRequest(String name, Long projectStatusId) {
        this.name = name;
        this.projectStatusId = projectStatusId;
    }
}
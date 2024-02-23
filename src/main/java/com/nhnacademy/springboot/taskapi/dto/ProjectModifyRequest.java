package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectModifyRequest {
    private Long id;
    private String name;
    private Long projectStatusId;

    public ProjectModifyRequest(Long id, String name, Long projectStatusId) {
        this.id = id;
        this.name = name;
        this.projectStatusId = projectStatusId;
    }
}

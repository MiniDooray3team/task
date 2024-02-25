package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRegisterRequest {
    private String name;

    public TagRegisterRequest(Long id, String name) {
        this.name = name;
    }
}

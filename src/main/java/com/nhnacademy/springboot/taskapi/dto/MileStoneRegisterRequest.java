package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MileStoneRegisterRequest {
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime stopAt;

    public MileStoneRegisterRequest(String name, LocalDateTime startAt, LocalDateTime stopAt) {
        this.name = name;
        this.startAt = startAt;
        this.stopAt = stopAt;
    }
}

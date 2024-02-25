package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MileStoneDto {
    private Long id;
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime stopAt;

    public MileStoneDto(Long id, String name, LocalDateTime startAt, LocalDateTime stopAt) {
        this.id = id;
        this.name = name;
        this.startAt = startAt;
        this.stopAt = stopAt;
    }
}

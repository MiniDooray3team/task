package com.nhnacademy.springboot.taskapi.dto;

import com.nhnacademy.springboot.taskapi.domain.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TaskHeader {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private String milestone;
    private TaskStatus taskStatus;
    private List<TagDto> tags;

    public TaskHeader() {

    }

    public TaskHeader(Long id, String name, LocalDateTime createdAt, String milestone, TaskStatus taskStatus, List<TagDto> tags) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.milestone = milestone;
        this.taskStatus = taskStatus;
        this.tags = tags;
    }
}
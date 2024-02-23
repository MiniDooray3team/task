package com.nhnacademy.springboot.taskapi.dto;

import com.nhnacademy.springboot.taskapi.domain.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskModifyRequest {
    private Long id;
    private String name;
    private String createdAt;
    private Long milestoneId;
    private String content;
    private TaskStatus taskStatus;
    private List<Tag> tags;

    @Getter
    @Setter
    public static class Status {
        private Long id;
        private String name;
    }

    @Getter
    @Setter
    public static class Tag {
        private Long id;
        private String name;
    }

    public TaskModifyRequest(Long id, String name, String createdAt, Long milestoneId, String content, TaskStatus taskStatus, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.milestoneId = milestoneId;
        this.content = content;
        this.taskStatus = taskStatus;
        this.tags = tags;
    }
}
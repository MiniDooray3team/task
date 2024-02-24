package com.nhnacademy.springboot.taskapi.dto;

import com.nhnacademy.springboot.taskapi.domain.TaskStatus;
import com.nhnacademy.springboot.taskapi.domain.TaskTag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskModifyRequest {
    private Long id;
    private String name;
    private Long milestoneId;
    private String content;
    private Long taskStatusId;
    private List<Long> tags;

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

    public TaskModifyRequest(Long id, String name, Long milestoneId, String content, Long taskStatusId, List<Long> tags) {
        this.id = id;
        this.name = name;
        this.milestoneId = milestoneId;
        this.content = content;
        this.taskStatusId = taskStatusId;
        this.tags = tags;
    }
}
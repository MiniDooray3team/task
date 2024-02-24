package com.nhnacademy.springboot.taskapi.dto;

import com.nhnacademy.springboot.taskapi.domain.MileStone;
import com.nhnacademy.springboot.taskapi.domain.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String name;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
    private String content;
    private MileStone mileStone;
    private Long admin_id;

    public TaskResponse(Long id, String name, TaskStatus taskStatus, LocalDateTime createdAt, String content, MileStone mileStone, Long admin_id) {
        this.id = id;
        this.name = name;
        this.taskStatus = taskStatus;
        this.createdAt = createdAt;
        this.content = content;
        this.mileStone = mileStone;
        this.admin_id = admin_id;
    }

    public TaskResponse() {

    }
}

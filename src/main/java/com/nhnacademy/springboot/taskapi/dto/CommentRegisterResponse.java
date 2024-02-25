package com.nhnacademy.springboot.taskapi.dto;

import com.nhnacademy.springboot.taskapi.domain.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRegisterResponse {
    private Long id;
    private Long writerId;
    private String content;
    private LocalDateTime createdAt;
    private Long taskId;

    public CommentRegisterResponse(Long id, Long writerId, String content, LocalDateTime createdAt, Long taskId) {
        this.id = id;
        this.writerId = writerId;
        this.content = content;
        this.createdAt = createdAt;
        this.taskId = taskId;
    }

    public CommentRegisterResponse() {

    }
}

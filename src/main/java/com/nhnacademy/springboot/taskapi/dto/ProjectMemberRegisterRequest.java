package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberRegisterRequest {
    private Long memberId;
    private Long projectId;

    public ProjectMemberRegisterRequest(Long memberId, Long projectId) {
        this.memberId = memberId;
        this.projectId = projectId;
    }
}

package com.nhnacademy.springboot.taskapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberDto {
    private Long memberId;

    public ProjectMemberDto(Long memberId) {
        this.memberId = memberId;
    }

    public ProjectMemberDto() {

    }
}

package com.nhnacademy.springboot.taskapi.dto;

import com.nhnacademy.springboot.taskapi.domain.MileStone;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskModifyRequest {
    private Long id;
    private String name;
    private MileStoneDto mileStoneDto;
    private String content;
    private Long taskStatusId;
    private List<TagDto> tags;

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

    public TaskModifyRequest(Long id, String name, MileStoneDto mileStoneDto, String content, Long taskStatusId, List<TagDto> tags) {
        this.id = id;
        this.name = name;
        this.mileStoneDto = mileStoneDto;
        this.content = content;
        this.taskStatusId = taskStatusId;
        this.tags = tags;
    }
}
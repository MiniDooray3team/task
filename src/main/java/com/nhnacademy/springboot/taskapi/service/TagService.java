package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Tag;
import com.nhnacademy.springboot.taskapi.dto.TagDto;
import com.nhnacademy.springboot.taskapi.dto.TagRegisterRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagService {
    List<TagDto> getTags(Long projectId);
    Tag createTag(TagRegisterRequest request, Long projectId, Long memberId);
    @Transactional
    void deleteTag(Long tagId, Long projectId, Long memberId);
}
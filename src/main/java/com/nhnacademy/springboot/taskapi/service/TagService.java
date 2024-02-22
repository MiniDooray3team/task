package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags();
    Tag getTag(Long id);
    Tag createTag(Tag project);
    void updateTag(Tag project);
    void deleteTag(Long id);
}
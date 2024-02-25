package com.nhnacademy.springboot.taskapi.repository;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByProject(Project project);
    Boolean existsByNameLike(String tagName);
}

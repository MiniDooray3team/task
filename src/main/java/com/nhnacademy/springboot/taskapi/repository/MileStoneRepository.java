package com.nhnacademy.springboot.taskapi.repository;

import com.nhnacademy.springboot.taskapi.domain.MileStone;
import com.nhnacademy.springboot.taskapi.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {
    List<MileStone> findByProject(Project project);
    Boolean existsByNameLike(String tagName);
}

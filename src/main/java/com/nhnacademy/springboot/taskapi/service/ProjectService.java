package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Project;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectService {
    List<Project> getProjects();
    Project getProject(Long id);
    Project createProject(Project project);
    void updateProject(Project project);
    void deleteProject(Long id);
}

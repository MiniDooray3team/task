package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;

import java.util.List;

public interface ProjectService {
    List<Project> getProjects();
    Project getProject(Long projectId);
    Project createProject(Project project);
    void updateProject(Project project);
    void deleteProject(Long projectId);
    void addProjectMember(ProjectMember projectMember, Long adminId);
    void deleteProjectMember(ProjectMember projectMember, Long adminId);
    List<Project> getProjectsByMemberId(Long memberId);
}

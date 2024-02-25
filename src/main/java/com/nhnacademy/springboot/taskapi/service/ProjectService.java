package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;
import com.nhnacademy.springboot.taskapi.dto.ProjectMemberRegisterRequest;
import com.nhnacademy.springboot.taskapi.dto.ProjectModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.ProjectRegisterRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectService {
    List<ProjectMember> getProjectsByMemberId(Long memberId);
    Project getProject(Long projectId, Long memberId);
    Project createProject(ProjectRegisterRequest request, Long memberId);
    void updateProject(ProjectModifyRequest request, Long adminId);
    void deleteProject(Long projectId, Long adminId);
    void addProjectMember(ProjectMemberRegisterRequest request, Long adminId);
    @Transactional
    void deleteProjectMember(ProjectMemberRegisterRequest request, Long adminId);
}
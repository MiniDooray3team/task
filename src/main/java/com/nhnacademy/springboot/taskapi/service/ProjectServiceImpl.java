package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;
import com.nhnacademy.springboot.taskapi.dto.ProjectMemberRegisterRequest;
import com.nhnacademy.springboot.taskapi.dto.ProjectModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.ProjectRegisterRequest;
import com.nhnacademy.springboot.taskapi.exception.*;
import com.nhnacademy.springboot.taskapi.repository.ProjectMemberRepository;
import com.nhnacademy.springboot.taskapi.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    @Override
    public List<ProjectMember> getProjectsByMemberId(Long memberId) {
        return projectMemberRepository.findProjectsByPk_MemberId(memberId);
    }

    @Override
    public Project getProject(Long projectId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)) {
            throw new ProjectMemberNotFoundException("User " + memberId + " is not a member of the project.");
        }
        return projectRepository.findById(projectId).orElse(null);
    }

    @Override
    public Project createProject(ProjectRegisterRequest request, Long memberId) {
        Project project = new Project();
        project.setName(request.getName());
        project.setCreatedAt(LocalDateTime.now());
        project.setAdminId(memberId);
        project.setProjectStatusId(request.getProjectStatusId());

        return projectRepository.save(project);
    }

    @Override
    public void updateProject(ProjectModifyRequest request, Long adminId) {
        Project project = projectRepository.findById(request.getId()).orElseThrow(ProjectNotFoundException::new);

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot update project.");
        }

        project.setName(request.getName());
        project.setProjectStatusId(request.getProjectStatusId());

        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId, Long adminId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot delete project.");
        }

        projectRepository.deleteById(projectId);
    }

    @Override
    public void addProjectMember(ProjectMemberRegisterRequest request, Long adminId) {
        Long projectId = request.getProjectId();
        Long memberId = request.getMemberId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot add project member.");
        }

        if(projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)) {
            throw new ProjectMemberAlreadyExistsException();
        }

        ProjectMember.Pk pk = new ProjectMember.Pk();
        pk.setProjectId(projectId);
        pk.setMemberId(memberId);

        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setPk(pk);

        projectMemberRepository.save(projectMember);
    }

    @Override
    public void deleteProjectMember(ProjectMemberRegisterRequest request, Long adminId) {
        Long projectId = request.getProjectId();
        Long memberId = request.getMemberId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot add project member.");
        }

        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)) {
            throw new ProjectMemberNotFoundException("User " + memberId + " is not a member of the project.");
        }

        projectMemberRepository.deleteProjectMemberByPk_ProjectIdAndPk_MemberId(projectId, memberId);
    }

}
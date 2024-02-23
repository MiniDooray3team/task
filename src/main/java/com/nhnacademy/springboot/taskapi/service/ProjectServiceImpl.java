package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;
import com.nhnacademy.springboot.taskapi.exception.*;
import com.nhnacademy.springboot.taskapi.repository.ProjectMemberRepository;
import com.nhnacademy.springboot.taskapi.repository.ProjectRepository;
import org.springframework.stereotype.Service;

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
    public List<Project> getProjectsByMemberId(Long memberId) {
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
    public Project createProject(Project project) {
        if(projectRepository.existsById(project.getId())){
            throw new ProjectAlreadyExistsException("project " + project.getId() + " already exists");
        }

        return projectRepository.save(project);
    }

    @Override
    public void updateProject(Project project, Long adminId) {
        if(!(projectRepository.existsById(project.getId()))){
            throw new ProjectNotFoundException("project " + project.getId() + " not found");
        }

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot update project.");
        }

        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId, Long adminId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null){
            throw new ProjectNotFoundException("project " + projectId + " not found");
        }

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot delete project.");
        }

        projectRepository.deleteById(projectId);
    }

    @Override
    public void addProjectMember(ProjectMember projectMember, Long adminId) {
        Long projectId = projectMember.getPk().getProjectId();
        Long memberId = projectMember.getPk().getMemberId();
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            throw new ProjectNotFoundException("project " + projectId + " not found");
        }

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot add project member.");
        }

        if(projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)) {
            throw new ProjectMemberAlreadyExistsException();
        }

        projectMemberRepository.save(projectMember);
    }

    @Override
    public void deleteProjectMember(ProjectMember projectMember, Long adminId) {
        Long projectId = projectMember.getPk().getProjectId();
        Long memberId = projectMember.getPk().getMemberId();
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) {
            throw new ProjectNotFoundException("project " + projectId + " not found");
        }

        if(!project.getAdminId().equals(adminId)) {
            throw new UnauthorizedUserException("Permission denied: You cannot delete project member.");
        }

        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)) {
            throw new ProjectMemberNotFoundException("User " + memberId + " is not a member of the project.");
        }

        projectMemberRepository.deleteProjectMemberByPk_ProjectIdAndPk_MemberId(projectId, memberId);
    }

}
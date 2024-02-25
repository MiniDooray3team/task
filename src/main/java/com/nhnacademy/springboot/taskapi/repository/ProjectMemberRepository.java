package com.nhnacademy.springboot.taskapi.repository;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
    ProjectMember save(ProjectMember projectMember);
    void deleteProjectMemberByPk_ProjectIdAndPk_MemberId(Long projectId, Long memberId);
    List<ProjectMember> findProjectsByPk_MemberId(Long memberId);
    List<ProjectMember> findAllByProject(Project project);
    boolean existsByPk_ProjectIdAndPk_MemberId(Long projectId, Long memberId);
    @Transactional
    void deleteByPk_ProjectId(Long projectId);
}

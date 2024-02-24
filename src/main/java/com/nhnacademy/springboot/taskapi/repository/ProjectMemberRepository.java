package com.nhnacademy.springboot.taskapi.repository;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
    ProjectMember save(ProjectMember projectMember);
    ProjectMember deleteProjectMemberByPk_ProjectIdAndPk_MemberId(Long projectId, Long memberId);
    List<ProjectMember> findProjectsByPk_MemberId(Long memberId);
    boolean existsByPk_ProjectIdAndPk_MemberId(Long projectId, Long memberId);
    void deleteByPk_ProjectId(Long projectId);
}

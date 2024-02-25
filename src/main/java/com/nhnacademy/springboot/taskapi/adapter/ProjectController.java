package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.dto.ProjectMemberDto;
import com.nhnacademy.springboot.taskapi.dto.ProjectMemberRegisterRequest;
import com.nhnacademy.springboot.taskapi.dto.ProjectModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.ProjectRegisterRequest;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;
import com.nhnacademy.springboot.taskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<Project> getProjectsByMemberId(@RequestHeader("MEMBER-SERIAL-ID") String memberId){
        List<ProjectMember> projectMembers = projectService.getProjectsByMemberId(Long.parseLong(memberId));
        List<Project> projects = projectMembers.stream()
                .map(ProjectMember::getProject)
                .collect(Collectors.toList());
        return projects;
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        return projectService.getProject(projectId, Long.parseLong(memberId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody ProjectRegisterRequest request, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        return projectService.createProject(request, Long.parseLong(memberId));
    }

    @PutMapping
    public ResultResponse updateProject(@RequestBody ProjectModifyRequest request, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        projectService.updateProject(request, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }

    @DeleteMapping("/{projectId}")
    public ResultResponse deleteProject(@PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        projectService.deleteProject(projectId, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultResponse addProjectMember(@RequestBody ProjectMemberRegisterRequest request, @RequestHeader("MEMBER-SERIAL-ID") String memberId){
        projectService.addProjectMember(request, Long.parseLong(memberId));
        return new ResultResponse("created");
    }

    @GetMapping("/{projectId}/member")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProjectMemberDto> getProjectMembers(@PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") String memberId){
        return projectService.getProjectMembers(projectId, Long.parseLong(memberId));
    }

    @DeleteMapping("/member")
    public ResultResponse deleteProjectMember(@RequestBody ProjectMemberRegisterRequest request, @RequestHeader("MEMBER-SERIAL-ID") String memberId){
        projectService.deleteProjectMember(request, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }

}
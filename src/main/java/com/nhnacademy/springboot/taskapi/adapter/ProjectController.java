package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.ProjectMember;
import com.nhnacademy.springboot.taskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable("projectId") Long projectId) {
        return projectService.getProject(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping
    public ResultResponse updateProject(@RequestBody Project project, @RequestHeader("MEMBER-SERIAL-ID") Long adminId) {
        projectService.updateProject(project, adminId);
        return new ResultResponse("ok");
    }

    @DeleteMapping("/{projectId}")
    public ResultResponse deleteProject(@PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") Long adminId) {
        projectService.deleteProject(projectId, adminId);
        return new ResultResponse("ok");
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultResponse addProjectMember(@RequestBody ProjectMember projectMember, @RequestHeader("MEMBER-SERIAL-ID") Long adminId){
        projectService.addProjectMember(projectMember, adminId);
        return new ResultResponse("created");
    }

    @DeleteMapping("/member")
    public ResultResponse deleteProjectMember(@RequestBody ProjectMember projectMember, @RequestHeader("MEMBER-SERIAL-ID") Long adminId){
        projectService.deleteProjectMember(projectMember, adminId);
        return new ResultResponse("ok");
    }

    @GetMapping("/member/{memberId}")
    public List<Project> getProjectsByMemberId(@PathVariable("memberId") Long memberId){
        return projectService.getProjectsByMemberId(memberId);
    }
}
package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Project;
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

    @GetMapping("/{id}")
    public Project getProject(@PathVariable("id") Long id) {
        return projectService.getProject(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping
    public ResultResponse updateProject(@RequestBody Project project) {
        projectService.updateProject(project);
        return new ResultResponse("ok");
    }

    @DeleteMapping("/{id}")
    public ResultResponse deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return new ResultResponse("ok");
    }
}

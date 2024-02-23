package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Task;
import com.nhnacademy.springboot.taskapi.dto.TaskModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.TaskRegisterRequest;
import com.nhnacademy.springboot.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getTasks(@PathVariable("projectId") Long projectId) {
        return taskService.getTasks(projectId);
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        return taskService.getTask(projectId, taskId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskRegisterRequest request, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        return taskService.createTask(request, Long.parseLong(memberId));
    }

    @PutMapping
    public ResultResponse updateTask(@PathVariable Long projectId, @RequestBody TaskModifyRequest request, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        taskService.updateTask(projectId, request, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }

    @DeleteMapping("/{taskId}")
    public ResultResponse deleteTask(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        taskService.deleteTask(projectId, taskId, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }
}

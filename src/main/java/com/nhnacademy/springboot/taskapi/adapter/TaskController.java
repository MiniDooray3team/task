package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Task;
import com.nhnacademy.springboot.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping
    public ResultResponse updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return new ResultResponse("ok");
    }

    @DeleteMapping
    public ResultResponse deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResultResponse("ok");
    }
}

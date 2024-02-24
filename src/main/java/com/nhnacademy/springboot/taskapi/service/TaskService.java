package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Task;
import com.nhnacademy.springboot.taskapi.dto.TaskModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.TaskRegisterRequest;

import java.util.List;

public interface TaskService {
    List<Task> getTasks(Long projectId);
    Task getTask(Long projectId, Long taskId);
    Task createTask(TaskRegisterRequest request, Long memberId);
    void updateTask(Long projectId, TaskModifyRequest request, Long memberId);
    void deleteTask(Long projectId, Long taskId, Long memberId);
}

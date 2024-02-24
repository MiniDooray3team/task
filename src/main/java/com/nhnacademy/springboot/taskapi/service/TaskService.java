package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Task;
import com.nhnacademy.springboot.taskapi.dto.TaskHeader;
import com.nhnacademy.springboot.taskapi.dto.TaskModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.TaskRegisterRequest;
import com.nhnacademy.springboot.taskapi.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    List<TaskHeader> getProjectTaskHeaders(Long projectId);
    TaskResponse getTask(Long taskId);
    Task createTask(Long projectId, TaskRegisterRequest request, Long memberId);
    void updateTask(Long projectId, TaskModifyRequest request, Long memberId);
    void deleteTask(Long projectId, Long taskId, Long memberId);
}

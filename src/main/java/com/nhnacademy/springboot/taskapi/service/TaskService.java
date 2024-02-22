package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Task;

import java.util.List;

public interface TaskService {
    Task getTask(Long id);
    List<Task> getTasks();
    Task createTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long id);
}

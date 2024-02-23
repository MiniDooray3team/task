package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.MileStone;
import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.Task;
import com.nhnacademy.springboot.taskapi.domain.TaskStatus;
import com.nhnacademy.springboot.taskapi.dto.TaskModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.TaskRegisterRequest;
import com.nhnacademy.springboot.taskapi.exception.MileStoneNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.ProjectMemberNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.springboot.taskapi.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final MileStoneRepository mileStoneRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, MileStoneRepository mileStoneRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.mileStoneRepository = mileStoneRepository;
    }

    @Override
    public List<Task> getTasks(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Task getTask(Long projectId, Long taskId) {
        return taskRepository.findByProjectIdAndId(projectId, taskId);
    }

    @Override
    public Task createTask(TaskRegisterRequest request, Long memberId) {
        Long projectId = request.getProjectId();
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        Task task = new Task();
        task.setCreatedAt(LocalDateTime.now());
        task.setName(request.getName());
        task.setAdminId(memberId);
        task.setProject(project);

        return taskRepository.save(task);
    }

    @Override
    public void updateTask(Long projectId, TaskModifyRequest request, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        Task task = taskRepository.findById(request.getId()).orElseThrow(TaskNotFoundException::new);
        MileStone mileStone = mileStoneRepository.findById(request.getMilestoneId()).orElseThrow(MileStoneNotFoundException::new);

        task.setMileStone(mileStone);
        task.setContent(request.getContent());
        task.setName(request.getName());
        task.setTaskStatus(request.getTaskStatus());

        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long projectId, Long taskId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        if(!taskRepository.existsById(taskId)){
            throw new TaskNotFoundException();
        }

        taskRepository.deleteById(taskId);
    }
}

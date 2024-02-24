package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.*;
import com.nhnacademy.springboot.taskapi.dto.TaskHeader;
import com.nhnacademy.springboot.taskapi.dto.TaskModifyRequest;
import com.nhnacademy.springboot.taskapi.dto.TaskRegisterRequest;
import com.nhnacademy.springboot.taskapi.exception.MileStoneNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.ProjectMemberNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.TaskNotFoundException;
import com.nhnacademy.springboot.taskapi.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final MileStoneRepository mileStoneRepository;
    private final TaskTagRepository taskTagRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, MileStoneRepository mileStoneRepository, TaskTagRepository taskTagRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.mileStoneRepository = mileStoneRepository;
        this.taskTagRepository = taskTagRepository;
    }

    @Override
    public List<TaskHeader> getProjectTaskHeaders(Long projectId) {
        List<Task> taskList = taskRepository.findAllByProjectId(projectId);
        List<TaskHeader> taskHeaderList = new ArrayList<>();
        for (Task task : taskList) {
            TaskHeader taskHeader = new TaskHeader();
            taskHeader.setId(task.getId());
            taskHeader.setName(task.getName());
            taskHeader.setCreatedAt(task.getCreatedAt());

            taskHeader.setMilestone(task.getMileStone().getName());
            taskHeader.setTaskStatus(task.getTaskStatus());

            List<TaskTag> taskTags = taskTagRepository.findByTaskId(task.getId());
            List<TaskHeader.TagDTO> tagDTOs = taskTags.stream()
                    .map(taskTag -> new TaskHeader.TagDTO(taskTag.getTag().getId(), taskTag.getTag().getName()))
                    .collect(Collectors.toList());
            taskHeader.setTags(tagDTOs);

            taskHeaderList.add(taskHeader);
        }

        return taskHeaderList;
    }

    @Override
    public Task getTask(Long projectId, Long taskId) {
        return taskRepository.findByProjectIdAndId(projectId, taskId);
    }

    @Override
    public Task createTask(Long projectId, TaskRegisterRequest request, Long memberId) {
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

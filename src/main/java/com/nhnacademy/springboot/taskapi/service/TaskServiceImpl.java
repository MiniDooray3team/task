package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.*;
import com.nhnacademy.springboot.taskapi.dto.*;
import com.nhnacademy.springboot.taskapi.exception.*;
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
    private final TaskStatusRepository taskStatusRepository;
    private final TagRepository tagRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, MileStoneRepository mileStoneRepository, TaskTagRepository taskTagRepository, TaskStatusRepository taskStatusRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.mileStoneRepository = mileStoneRepository;
        this.taskTagRepository = taskTagRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TaskHeader> getProjectTaskHeaders(Long projectId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        List<Task> taskList = taskRepository.findAllByProjectId(projectId);
        List<TaskHeader> taskHeaderList = new ArrayList<>();
        for (Task task : taskList) {
            TaskHeader taskHeader = new TaskHeader();
            taskHeader.setId(task.getId());
            taskHeader.setName(task.getName());
            taskHeader.setCreatedAt(task.getCreatedAt());

            if (task.getMileStone() != null) {
                taskHeader.setMilestone(task.getMileStone().getName());
            }
            if (task.getTaskStatus() != null) {
                taskHeader.setTaskStatus(task.getTaskStatus());
            }

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
    public TaskResponse getTask(Long taskId, Long projectId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));

        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setTaskStatus(task.getTaskStatus());
        taskResponse.setMileStone(task.getMileStone());
        taskResponse.setCreatedAt(task.getCreatedAt());
        taskResponse.setContent(task.getContent());

        return taskResponse;
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
        task.setName(request.getName());
        task.setContent(request.getContent());

        MileStone mileStone = mileStoneRepository.findById(request.getMilestoneId()).orElseThrow(MileStoneNotFoundException::new);
        task.setMileStone(mileStone);

        TaskStatus taskStatus = taskStatusRepository.findById(request.getTaskStatusId()).orElseThrow(TaskStatusNotFoundException::new);
        task.setTaskStatus(taskStatus);

        taskRepository.save(task);

        for (Long tagId : request.getTags()) {
            if(!taskTagRepository.existsByTagIdAndTaskId(tagId, task.getId())){
                TaskTag taskTag = new TaskTag();

                Tag tag = tagRepository.findById(tagId).orElseThrow(ProjectNotFoundException::new);
                taskTag.setTag(tag);
                taskTag.setTask(task);

                taskTagRepository.save(taskTag);
            }
        }
    }

    @Override
    public void deleteTask(Long projectId, Long taskId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        if(!taskRepository.existsById(taskId)){
            throw new TaskNotFoundException();
        }

        List<TaskTag> taskTags = taskTagRepository.findByTaskId(taskId);
        taskTagRepository.deleteAll(taskTags);

        taskRepository.deleteById(taskId);
    }
}

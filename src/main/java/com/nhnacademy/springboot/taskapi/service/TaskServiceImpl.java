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
    private final CommentRepository commentRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, MileStoneRepository mileStoneRepository, TaskTagRepository taskTagRepository, TaskStatusRepository taskStatusRepository, TagRepository tagRepository, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.mileStoneRepository = mileStoneRepository;
        this.taskTagRepository = taskTagRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
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
            List<TagDto> tagDTOs = taskTags.stream()
                    .map(taskTag -> new TagDto(taskTag.getTag().getId(), taskTag.getTag().getName()))
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
        taskResponse.setAdmin_id(task.getAdminId());
        taskResponse.setContent(task.getContent());

        return taskResponse;
    }

    @Override
    public Task createTask(Long projectId, TaskRegisterRequest request, Long memberId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        TaskStatus defaultTaskStatus = taskStatusRepository.findById(1L).orElseThrow(TaskStatusNotFoundException::new);

        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        Task task = new Task();
        task.setCreatedAt(LocalDateTime.now());
        task.setName(request.getName());
        task.setAdminId(memberId);
        task.setProject(project);
        task.setTaskStatus(defaultTaskStatus);

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

        if(request.getMileStoneDto() != null){
            MileStone mileStone = mileStoneRepository.findById(request.getMileStoneDto().getId()).orElseThrow(MileStoneNotFoundException::new);
            task.setMileStone(mileStone);
        }

        TaskStatus taskStatus = taskStatusRepository.findById(request.getTaskStatusId()).orElseThrow(TaskStatusNotFoundException::new);
        task.setTaskStatus(taskStatus);

        taskRepository.save(task);


        for (TagDto tagDto : request.getTags()) {
            if(!taskTagRepository.existsByTagIdAndTaskId(tagDto.getId(), task.getId())){
                TaskTag taskTag = new TaskTag();

                Tag tag = tagRepository.findById(tagDto.getId()).orElseThrow(TagNotFoundException::new);
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

        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        commentRepository.deleteAllByTask(task);

        taskRepository.deleteById(taskId);
    }
}

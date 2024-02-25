package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.*;
import com.nhnacademy.springboot.taskapi.dto.CommentDto;
import com.nhnacademy.springboot.taskapi.dto.CommentRegisterResponse;
import com.nhnacademy.springboot.taskapi.dto.TaskResponse;
import com.nhnacademy.springboot.taskapi.exception.*;
import com.nhnacademy.springboot.taskapi.repository.CommentRepository;
import com.nhnacademy.springboot.taskapi.repository.ProjectMemberRepository;
import com.nhnacademy.springboot.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository, ProjectMemberRepository projectMemberRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    @Override
    public List<CommentDto> getComments(Long taskId, Long memberId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        List<Comment> comments = commentRepository.findByTask(task);
        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> new CommentDto(comment.getId(), comment.getWriterId(), comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt()))
                .collect(Collectors.toList());

        return commentDtos;
    }

    @Override
    public CommentRegisterResponse createComment(Long projectId, Long taskId, CommentDto request, Long memberId) {
        Task task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        Comment comment = new Comment();
        comment.setCreatedAt(LocalDateTime.now());
        comment.setContent(request.getContent());
        comment.setWriterId(memberId);
        comment.setTask(task);
        commentRepository.save(comment);

        CommentRegisterResponse commentRegisterResponse = new CommentRegisterResponse();
        commentRegisterResponse.setId(comment.getId());
        commentRegisterResponse.setCreatedAt(comment.getCreatedAt());
        commentRegisterResponse.setContent(comment.getContent());
        commentRegisterResponse.setTaskId(comment.getTask().getId());
        commentRegisterResponse.setWriterId(comment.getWriterId());

        return commentRegisterResponse;
    }

    @Override
    public void updateComment(Long projectId, CommentDto request, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        Comment comment = commentRepository.findById(request.getId()).orElseThrow(CommentNotFoundException::new);

        if (!comment.getWriterId().equals(memberId)) {
            throw new UnauthorizedUserException("You are not authorized to update this comment.");
        }

        if (request.getContent() != null) {
            comment.setContent(request.getContent());
            comment.setUpdatedAt(LocalDateTime.now());
        }
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long projectId, Long commentId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        if(!commentRepository.existsById(commentId)){
            throw new CommentNotFoundException();
        }

        commentRepository.deleteById(commentId);
    }
}

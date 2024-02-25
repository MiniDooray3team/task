package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.dto.CommentDto;
import com.nhnacademy.springboot.taskapi.dto.CommentRegisterResponse;

import java.util.List;

public interface CommentService {
    List<CommentDto> getComments(Long taskId, Long memberId);
    CommentRegisterResponse createComment(Long projectId, Long taskId, CommentDto request, Long memberId);
    void updateComment(Long projectId, CommentDto request, Long memberId);
    void deleteComment(Long projectId, Long commentId, Long memberId);
}

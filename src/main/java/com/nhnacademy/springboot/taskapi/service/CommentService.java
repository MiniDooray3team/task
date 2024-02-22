package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments();
    Comment createComment(Comment project);
    void updateComment(Comment project);
    void deleteComment(Long id);
}

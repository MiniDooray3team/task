package com.nhnacademy.springboot.taskapi.repository;

import com.nhnacademy.springboot.taskapi.domain.Comment;
import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.Tag;
import com.nhnacademy.springboot.taskapi.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
}

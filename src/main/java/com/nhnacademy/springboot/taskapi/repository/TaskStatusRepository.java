package com.nhnacademy.springboot.taskapi.repository;

import com.nhnacademy.springboot.taskapi.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}

package com.nhnacademy.springboot.taskapi.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name= "project_id")
    @ManyToOne
    Project project;

    @JoinColumn(name = "milestone_id")
    @ManyToOne
    MileStone mileStone;

    @Column(name = "name")
    String name;

    @Column(name = "admin_id")
    Long adminId;

    //created_at
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "content")
    String content;

    @JoinColumn(name = "task_status_id")
    @ManyToOne
    TaskStatus taskStatus;




}

package com.nhnacademy.springboot.taskapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
    @ManyToOne(cascade = CascadeType.REMOVE)
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

    @OneToMany(mappedBy = "task")
    List<TaskTag> taskTags;

}

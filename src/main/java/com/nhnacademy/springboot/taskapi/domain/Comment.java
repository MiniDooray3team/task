package com.nhnacademy.springboot.taskapi.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "task_id")
    @ManyToOne
    Task task;

    @Column(name = "content")
    String content;

    @Column(name = "writer_id")
    Long writerId;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}

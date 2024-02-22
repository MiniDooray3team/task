package com.nhnacademy.springboot.taskapi.domain;

import javax.persistence.*;

@Entity
@Table(name = "task_tag")
public class TaskTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "task_id")
    @ManyToOne
    Task task;

    @JoinColumn(name = "tag_id")
    @ManyToOne
    Tag tag;

}

package com.nhnacademy.springboot.taskapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

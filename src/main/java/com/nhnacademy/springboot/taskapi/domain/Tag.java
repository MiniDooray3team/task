package com.nhnacademy.springboot.taskapi.domain;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "project_id")
    @ManyToOne
    Project projectId;

    @Column(name = "name")
    String name;

}

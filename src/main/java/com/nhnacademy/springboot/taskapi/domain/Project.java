package com.nhnacademy.springboot.taskapi.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "project_status_id")
    int projectStatusId;

    @Column(name = "admin_id")
    Long adminId;


}

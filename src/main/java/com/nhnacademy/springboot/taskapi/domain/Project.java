package com.nhnacademy.springboot.taskapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
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
    Long projectStatusId;

    @Column(name = "admin_id")
    Long adminId;


}

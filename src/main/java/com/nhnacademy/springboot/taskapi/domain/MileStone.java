package com.nhnacademy.springboot.taskapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "mile_stone")
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "project_id")
    @ManyToOne
    Project project;

    @Column(name = "name")
    String name;

    @Column(name = "start_at")
    LocalDateTime startAt;

    @Column(name = "stop_at")
    LocalDateTime stopAt;

}

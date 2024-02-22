package com.nhnacademy.springboot.taskapi.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_member")
public class ProjectMember {

    @EmbeddedId
    Pk pk;

    @JoinColumn(name = "project_id")
    @MapsId("projectId")
    @ManyToOne
    Project project;

    @Embeddable
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "project_id")
        Long projectId;
        @Column(name = "member_id")
        Long memberId;
    }

}

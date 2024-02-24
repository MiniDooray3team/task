package com.nhnacademy.springboot.taskapi.repository;

import com.nhnacademy.springboot.taskapi.domain.Project;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@SpringBootTest
@Transactional
class ProjectRepositoryTest {

        @Autowired
        ProjectRepository projectRepository;

        @Test
        void getProject() {
                Project project = new Project(1L, "미니", LocalDateTime.of(2024,2,25,05,33,43), 1L, 1L);
                Optional<Project> actual = projectRepository.findById(1L);
                Assertions.assertThat(actual).isPresent();
                Assertions.assertThat(actual.get()).usingRecursiveComparison().isEqualTo(project);
        }


}
package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.domain.Tag;
import com.nhnacademy.springboot.taskapi.dto.TagDto;
import com.nhnacademy.springboot.taskapi.dto.TagRegisterRequest;
import com.nhnacademy.springboot.taskapi.exception.ProjectMemberNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.springboot.taskapi.repository.ProjectMemberRepository;
import com.nhnacademy.springboot.taskapi.repository.ProjectRepository;
import com.nhnacademy.springboot.taskapi.repository.TagRepository;
import com.nhnacademy.springboot.taskapi.repository.TaskTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final TaskTagRepository taskTagRepository;

    public TagServiceImpl(TagRepository tagRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, TaskTagRepository taskTagRepository) {
        this.tagRepository = tagRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.taskTagRepository = taskTagRepository;
    }

    @Override
    public List<TagDto> getTags(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        List<Tag> tags = tagRepository.findByProject(project);
        List<TagDto> tagDTOs = tags.stream()
                .map(tag -> new TagDto(tag.getId(), tag.getName()))
                .collect(Collectors.toList());

        return tagDTOs;
    }

    @Override
    public Tag createTag(TagRegisterRequest request, Long projectId, Long memberId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        if(tagRepository.existsByNameLike(request.getName().toLowerCase())){
            throw new IllegalStateException("Tag \'" + request.getName() + "\' already exists");
        }

        Tag tag = new Tag();
        tag.setName(request.getName());
        tag.setProject(project);
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long tagId, Long projectId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        taskTagRepository.deleteAllByTagId(tagId);
        tagRepository.deleteById(tagId);

    }
}


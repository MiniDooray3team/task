package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.MileStone;
import com.nhnacademy.springboot.taskapi.domain.Project;
import com.nhnacademy.springboot.taskapi.dto.MileStoneDto;
import com.nhnacademy.springboot.taskapi.dto.MileStoneRegisterRequest;
import com.nhnacademy.springboot.taskapi.exception.MileStoneAlreadyExistsException;
import com.nhnacademy.springboot.taskapi.exception.ProjectMemberNotFoundException;
import com.nhnacademy.springboot.taskapi.exception.ProjectNotFoundException;
import com.nhnacademy.springboot.taskapi.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MileStoneServiceImpl implements MileStoneService {
    private final MileStoneRepository mileStoneRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public MileStoneServiceImpl(MileStoneRepository mileStoneRepository, ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository) {
        this.mileStoneRepository = mileStoneRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    @Override
    public List<MileStoneDto> getMileStones(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        List<MileStone> mileStones = mileStoneRepository.findByProject(project);
        List<MileStoneDto> mileStoneDtos = mileStones.stream()
                .map(mileStone -> new MileStoneDto(mileStone.getId(), mileStone.getName(), mileStone.getStartAt(), mileStone.getStopAt()))
                .collect(Collectors.toList());

        return mileStoneDtos;
    }

    @Override
    public MileStone createMileStone(MileStoneRegisterRequest request, Long projectId, Long memberId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        if(mileStoneRepository.existsByNameLike(request.getName().toLowerCase())){
            throw new MileStoneAlreadyExistsException();
        }

        MileStone mileStone = new MileStone();
        mileStone.setName(request.getName());
        mileStone.setProject(project);
        mileStone.setStopAt(request.getStopAt());
        mileStone.setStartAt(request.getStartAt());
        return mileStoneRepository.save(mileStone);
    }

    @Override
    public void deleteMileStone(Long mileStoneId, Long projectId, Long memberId) {
        if(!projectMemberRepository.existsByPk_ProjectIdAndPk_MemberId(projectId, memberId)){
            throw new ProjectMemberNotFoundException();
        }

        mileStoneRepository.deleteById(mileStoneId);
    }
}

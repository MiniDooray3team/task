package com.nhnacademy.springboot.taskapi.service;

import com.nhnacademy.springboot.taskapi.domain.MileStone;
import com.nhnacademy.springboot.taskapi.dto.MileStoneDto;
import com.nhnacademy.springboot.taskapi.dto.MileStoneRegisterRequest;
import com.nhnacademy.springboot.taskapi.dto.TagRegisterRequest;

import java.util.List;

public interface MileStoneService {
    List<MileStoneDto> getMileStones(Long projectId);
    MileStone createMileStone(MileStoneRegisterRequest request, Long projectId, Long memberId);
    void deleteMileStone(Long mileStoneId, Long projectId, Long memberId);
}

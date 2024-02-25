package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.MileStone;
import com.nhnacademy.springboot.taskapi.domain.Tag;
import com.nhnacademy.springboot.taskapi.dto.MileStoneDto;
import com.nhnacademy.springboot.taskapi.dto.MileStoneRegisterRequest;
import com.nhnacademy.springboot.taskapi.dto.TagRegisterRequest;
import com.nhnacademy.springboot.taskapi.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/mileStone")
public class MileStoneController {
    private final MileStoneService mileStoneService;

    @GetMapping
    public List<MileStoneDto> getMilsStones(@PathVariable("projectId") Long projectId) {
        return mileStoneService.getMileStones(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MileStone createTag(@RequestBody MileStoneRegisterRequest request, @PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        return mileStoneService.createMileStone(request, projectId, Long.parseLong(memberId));
    }

    @DeleteMapping("/{mileStoneId}")
    public ResultResponse deleteTag(@PathVariable("mileStoneId") Long mileStoneId, @PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        mileStoneService.deleteMileStone(mileStoneId, projectId, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }
}

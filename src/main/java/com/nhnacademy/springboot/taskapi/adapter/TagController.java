package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Tag;
import com.nhnacademy.springboot.taskapi.dto.TagDto;
import com.nhnacademy.springboot.taskapi.dto.TagRegisterRequest;
import com.nhnacademy.springboot.taskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public List<TagDto> getTags(@PathVariable("projectId") Long projectId) {
        return tagService.getTags(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createTag(@RequestBody TagRegisterRequest request, @PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        return tagService.createTag(request, projectId, Long.parseLong(memberId));
    }

    @DeleteMapping("/{tagId}")
    public ResultResponse deleteTag(@PathVariable("tagId") Long tagId, @PathVariable("projectId") Long projectId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        tagService.deleteTag(tagId, projectId, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }
}

package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Tag;
import com.nhnacademy.springboot.taskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public List<Tag> getTags() {
        return tagService.getTags();
    }

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable("id") Long id) {
        return tagService.getTag(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @PutMapping
    public ResultResponse updateTag(@RequestBody Tag tag) {
        tagService.updateTag(tag);
        return new ResultResponse("ok");
    }

    @DeleteMapping("/{id}")
    public ResultResponse deleteTag(@PathVariable("id") Long id) {
        tagService.deleteTag(id);
        return new ResultResponse("ok");
    }
}

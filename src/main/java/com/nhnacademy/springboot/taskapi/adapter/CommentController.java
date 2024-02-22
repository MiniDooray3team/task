package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.domain.Comment;
import com.nhnacademy.springboot.taskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<Comment> getComments() {
        return commentService.getComments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping
    public ResultResponse updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);
        return new ResultResponse("ok");
    }

    @DeleteMapping("/{id}")
    public ResultResponse deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResultResponse("ok");
    }
}

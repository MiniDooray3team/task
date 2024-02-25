package com.nhnacademy.springboot.taskapi.adapter;

import com.nhnacademy.springboot.taskapi.dto.CommentDto;
import com.nhnacademy.springboot.taskapi.dto.CommentRegisterResponse;
import com.nhnacademy.springboot.taskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/task/{taskId}/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getComments(@PathVariable("taskId") Long taskId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        return commentService.getComments(taskId, Long.parseLong(memberId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentRegisterResponse createComment(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, @RequestBody CommentDto request, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        return commentService.createComment(projectId, taskId, request, Long.parseLong(memberId));
    }

    @PutMapping
    public ResultResponse updateComment(@PathVariable("projectId") Long projectId, @RequestBody CommentDto request, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        commentService.updateComment(projectId, request, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }

    @DeleteMapping("/{commentId}")
    public ResultResponse deleteComment(@PathVariable("projectId") Long projectId, @PathVariable("commentId") Long commentId, @RequestHeader("MEMBER-SERIAL-ID") String memberId) {
        commentService.deleteComment(projectId, commentId, Long.parseLong(memberId));
        return new ResultResponse("ok");
    }
}

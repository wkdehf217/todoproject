package com.todoproject.todoproject.controller;

import com.todoproject.todoproject.dto.comment.CommentUpdateRequestDto;
import com.todoproject.todoproject.dto.comment.CommentResponseDto;
import com.todoproject.todoproject.dto.comment.CommentRequestDto;
import com.todoproject.todoproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);    // ?
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }
}

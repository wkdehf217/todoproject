package com.todoproject.todoproject.controller;

import com.todoproject.todoproject.dto.comment.CommentDeleteRequestDto;
import com.todoproject.todoproject.dto.comment.CommentDeleteResponseDto;
import com.todoproject.todoproject.dto.comment.CommentUpdateRequestDto;
import com.todoproject.todoproject.dto.comment.CommentResponseDto;
import com.todoproject.todoproject.dto.comment.CommentRequestDto;
import com.todoproject.todoproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("댓글 삭제 성공하였습니다. 상태코드 : " + HttpStatus.OK , HttpStatus.OK);
    }
}

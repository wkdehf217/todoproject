package com.todoproject.todoproject.service;

import com.todoproject.todoproject.dto.comment.CommentResponseDto;
import com.todoproject.todoproject.dto.comment.CommentRequestDto;
import com.todoproject.todoproject.dto.comment.CommentUpdateRequestDto;
import com.todoproject.todoproject.entity.Comment;
import com.todoproject.todoproject.entity.Todo;
import com.todoproject.todoproject.repository.CommentRepository;
import com.todoproject.todoproject.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "up")
@Service
@RequiredArgsConstructor
public class CommentService {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Todo todo = todoRepository.findBytodoid(requestDto.getTodoid()).orElseThrow(() ->
                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
        );

        Comment comment = commentRepository.save(new Comment(requestDto, todo));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findByCommentid(id).orElseThrow(() ->
                new NullPointerException("해당 댓글을 찾을 수 없습니다.")
        );

        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteByCommentid(id).orElseThrow(() ->
                new NullPointerException("해당 댓글을 찾을 수 없습니다.")
        );
    }
}


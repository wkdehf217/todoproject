package com.todoproject.todoproject.dto.comment;

import com.todoproject.todoproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String content;
    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
    }
}
package com.todoproject.todoproject.dto.comment;

import com.todoproject.todoproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteResponseDto {
    private String content;
    public CommentDeleteResponseDto(Comment comment) {
        this.content = comment.getContent();
    }
}
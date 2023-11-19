package com.todoproject.todoproject.dto.todo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateRequestDto {
    String title;
    String content;
}

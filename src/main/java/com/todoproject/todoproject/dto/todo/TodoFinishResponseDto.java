package com.todoproject.todoproject.dto.todo;

import com.todoproject.todoproject.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class TodoFinishResponseDto {
    private Boolean finish;


    public TodoFinishResponseDto(Todo todo) {
        this.finish = todo.getFinish();
    }
}
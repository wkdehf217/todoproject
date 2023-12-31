package com.todoproject.todoproject.dto.todo;

import com.todoproject.todoproject.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodosResponseDto {
    private String title;
    private String maker;
    private String date;
    private Boolean finish;


    public TodosResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.maker = todo.getMaker();
        this.date = todo.getDate();
        this.finish = todo.getFinish();
    }
}

package com.todoproject.todoproject.dto.todo;


import com.todoproject.todoproject.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoResponseDto {
    private String title;
    private String content;
    private String maker;
    private String date;


    public TodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.maker = todo.getMaker();
        this.date = todo.getDate();
    }
}

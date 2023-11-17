package com.todoproject.todoproject.dto.todo;

import com.todoproject.todoproject.entity.Todo;

public class TodosResponseDto {
    //private Long todoid;
    private String title;
    //private String content;
    private String maker;
    private String date;
    private boolean finish;


    public TodosResponseDto(Todo todo) {
        //this.todoid = todo.getTodoid();
        this.title = todo.getTitle();
        //this.content = todo.getContent();
        this.maker = todo.getMaker();
        this.date = todo.getDate();
        this.finish = todo.isFinish();
    }
}

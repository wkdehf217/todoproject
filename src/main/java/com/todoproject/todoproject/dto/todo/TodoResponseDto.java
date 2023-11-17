package com.todoproject.todoproject.dto.todo;


import com.todoproject.todoproject.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoResponseDto {
    //private Long todoid;
    private String title;
    //private String content;
    private String maker;
    private String date;
    private boolean finish;


    public TodoResponseDto(Todo todo) {
        //this.todoid = todo.getTodoid();
        this.title = todo.getTitle();
        //this.content = todo.getContent();
        this.maker = todo.getMaker();
        this.date = todo.getDate();
        this.finish = todo.isFinish();
    }
}

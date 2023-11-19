package com.todoproject.todoproject.controller;


import com.todoproject.todoproject.dto.todo.*;
import com.todoproject.todoproject.security.UserDetailsImpl;
import com.todoproject.todoproject.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoService.createTodo(requestDto, userDetails.getUser());    // ?
    }

    @PutMapping("/todo/{id}")
    public TodoUpdateResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    @GetMapping("/todos")
    public List<TodosResponseDto> getTodos(){
        return todoService.getTodos();
    }

    @GetMapping("/todo/{id}")
    public TodoResponseDto getTodoById(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    @PutMapping("/todo-finish/{id}")
    public TodoFinishResponseDto finishTodo(@PathVariable Long id) {
        return todoService.finishTodo(id);
    }
}

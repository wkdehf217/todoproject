package com.todoproject.todoproject.controller;


import com.todoproject.todoproject.dto.TodoRequestDto;
import com.todoproject.todoproject.dto.TodoResponseDto;
import com.todoproject.todoproject.dto.TodoTitleContentRequestDto;
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
        System.out.println("hu");
        log.info("hu");
        return todoService.createTodo(requestDto, userDetails.getUser());    // ?
    }

    @PutMapping("/todo/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoTitleContentRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    @GetMapping("/todos")
    public List<TodoResponseDto> getTodos(){
        return todoService.getTodos();
    }
}

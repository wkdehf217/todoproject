package com.todoproject.todoproject.service;


import com.todoproject.todoproject.dto.TodoRequestDto;
import com.todoproject.todoproject.dto.TodoResponseDto;
import com.todoproject.todoproject.dto.TodoTitleContentRequestDto;
import com.todoproject.todoproject.entity.Todo;
import com.todoproject.todoproject.entity.User;
import com.todoproject.todoproject.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        Todo todo = todoRepository.save(new Todo(requestDto, user));
        return new TodoResponseDto(todo);
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoTitleContentRequestDto requestDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
        );

        todo.update(requestDto);

        return new TodoResponseDto(todo);
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> getTodos() {
        List<Todo> todoList = todoRepository.findAll();
        List<TodoResponseDto> responseDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            responseDtoList.add(new TodoResponseDto(todo));
        }

        return responseDtoList;
    }
}
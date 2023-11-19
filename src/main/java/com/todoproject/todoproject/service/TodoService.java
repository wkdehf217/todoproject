package com.todoproject.todoproject.service;


import com.todoproject.todoproject.dto.todo.*;
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
    public TodoUpdateResponseDto updateTodo(Long id, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findBytodoid(id).orElseThrow(() ->
                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
        );

        todo.update(requestDto);

        return new TodoUpdateResponseDto(todo);
    }

    @Transactional
    public TodoFinishResponseDto finishTodo(Long id) {
        Todo todo = todoRepository.findBytodoid(id).orElseThrow(() ->
                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
        );

        todo.finish();

        return new TodoFinishResponseDto(todo);
    }

    @Transactional(readOnly = true)
    public List<TodosResponseDto> getTodos() {
        List<Todo> todoList = todoRepository.findAllByOrderByDateDesc();
        List<TodosResponseDto> responseDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            responseDtoList.add(new TodosResponseDto(todo));
        }

        return responseDtoList;
    }

    @Transactional(readOnly = true)
    public TodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findBytodoid(id).orElseThrow(() ->
                new NullPointerException("해당 게시글을 찾을 수 없습니다.")
        );

        return new TodoResponseDto(todo);
    }
}

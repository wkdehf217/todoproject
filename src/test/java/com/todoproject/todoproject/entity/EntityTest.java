package com.todoproject.todoproject.entity;

import com.todoproject.todoproject.dto.todo.TodoRequestDto;
import com.todoproject.todoproject.dto.todo.TodoUpdateRequestDto;
import com.todoproject.todoproject.dto.todo.TodoUpdateResponseDto;
import com.todoproject.todoproject.repository.TodoRepository;
import com.todoproject.todoproject.repository.UserRepository;
import com.todoproject.todoproject.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EntityTest {
    @Mock
    UserRepository userRepository;

    @Mock
    TodoRepository todoRepository;

    // Entity test
    @Test
    void 유저생성테스트(){
        //given
        String username = "g";
        String password = "1";

        //when
        User user = new User(username,password);
        userRepository.save(user);

        System.out.println(user.getUsername());
        //then
        assertEquals("c", user.getUsername());
    }

    // Dto test
    @Test
    void todo업데이트테스트(){
        //given
        Long todoId = 1L;
        String title = "bye";
        String content = "byebye";

        TodoUpdateRequestDto todoUpdateRequestDto = new TodoUpdateRequestDto();
        todoUpdateRequestDto.setTitle(title);
        todoUpdateRequestDto.setContent(content);

        User user = new User();
        TodoRequestDto requestDto = new TodoRequestDto(
                "hi",
                "hihi",
                "hj"
        );

        Todo todo = new Todo(requestDto, user);
        TodoService todoService = new TodoService(todoRepository);
        given(todoRepository.findBytodoid(todoId)).willReturn(Optional.of(todo));

        //when
        TodoUpdateResponseDto result = todoService.updateTodo(todoId,todoUpdateRequestDto);

        //then
        assertEquals(content, result.getContent());
    }
}
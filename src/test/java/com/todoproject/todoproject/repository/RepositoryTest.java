package com.todoproject.todoproject.repository;

import com.todoproject.todoproject.dto.comment.CommentRequestDto;
import com.todoproject.todoproject.dto.comment.CommentResponseDto;
import com.todoproject.todoproject.dto.todo.TodoRequestDto;
import com.todoproject.todoproject.dto.todo.TodoResponseDto;
import com.todoproject.todoproject.dto.todo.TodoUpdateRequestDto;
import com.todoproject.todoproject.dto.todo.TodoUpdateResponseDto;
import com.todoproject.todoproject.entity.Todo;
import com.todoproject.todoproject.entity.User;
import com.todoproject.todoproject.service.CommentService;
import com.todoproject.todoproject.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    CommentRepository commentRepository;

    TodoResponseDto createdTodo = null;

    // teat(api): 유저 생성 기능 실패 테스트 추가
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

    @Test
    void 할일생성테스트(){
        // given
        String title = "hi";
        String content = "me";
        String maker = "who";

        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content,
                maker
        );

        User user = userRepository.findById(1L).orElse(null);

        TodoService todoService = new TodoService(todoRepository);

        // when
        TodoResponseDto todo = todoService.createTodo(requestDto, user);

        // then
        assertEquals(title, todo.getTitle());
        assertEquals(content, todo.getContent());
        assertEquals(maker, todo.getMaker());
        createdTodo = todo;
    }

    @Test
    void 신규댓글등록() {
        // given
        // 위에서 todo의 아이디를 넘겨주지 않기 때문에 임의로 지정
        Long todoId = 1L;
        String content = "comment";

        CommentRequestDto requestDto = new CommentRequestDto(
                todoId,
                content
        );

        CommentService commentService = new CommentService(todoRepository,commentRepository);

        // when
        CommentResponseDto comment = commentService.createComment(requestDto);

        // then
        assertEquals(content, comment.getContent());
    }
}

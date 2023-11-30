package com.todoproject.todoproject.entity;

import com.todoproject.todoproject.dto.comment.CommentRequestDto;
import com.todoproject.todoproject.dto.comment.CommentResponseDto;
import com.todoproject.todoproject.dto.comment.CommentUpdateRequestDto;
import com.todoproject.todoproject.dto.todo.TodoRequestDto;
import com.todoproject.todoproject.dto.todo.TodoResponseDto;
import com.todoproject.todoproject.repository.CommentRepository;
import com.todoproject.todoproject.repository.TodoRepository;
import com.todoproject.todoproject.repository.UserRepository;
import com.todoproject.todoproject.service.CommentService;
import com.todoproject.todoproject.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 서버의 PORT 를 랜덤으로 설정합니다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 인스턴스의 생성 단위를 클래스로 변경합니다.
@Transactional
public class EntityIntegrationTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoService todoService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    User user;

    // 응답할때 todoId를 넘겨주지 않게 설계되었음
    TodoResponseDto createdTodo = null;
    CommentResponseDto createdResponseDto = null;

    // Integration test
    @Test
    void 신규할일등록() {
        // given
        String title = "hi";
        String content = "me";
        String maker = "who";

        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content,
                maker
        );
        user = userRepository.findById(1L).orElse(null);

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

        // when
        CommentResponseDto comment = commentService.createComment(requestDto);

        // then
        assertEquals(content, comment.getContent());
        createdResponseDto = comment;
    }

    @Test
    void 댓글수정() {
        // given
        Long commentId = 1L;
        String content = "updateComment";

        CommentUpdateRequestDto commentUpdateRequestDto = new CommentUpdateRequestDto(
                content
        );

        // when
        CommentResponseDto responseDto = commentService.updateComment(commentId, commentUpdateRequestDto);

        // then
        assertEquals(content, responseDto.getContent());
    }
}
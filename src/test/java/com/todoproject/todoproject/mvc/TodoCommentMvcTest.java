package com.todoproject.todoproject.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todoproject.todoproject.controller.CommentController;
import com.todoproject.todoproject.controller.TodoController;
import com.todoproject.todoproject.dto.comment.CommentUpdateRequestDto;
import com.todoproject.todoproject.dto.todo.TodoRequestDto;
import com.todoproject.todoproject.dto.todo.TodoUpdateRequestDto;
import com.todoproject.todoproject.entity.User;
import com.todoproject.todoproject.security.UserDetailsImpl;
import com.todoproject.todoproject.service.CommentService;
import com.todoproject.todoproject.service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.DataInput;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = {TodoController.class, CommentController.class}
//        excludeFilters = {
//                @ComponentScan.Filter(
//                        type = FilterType.ASSIGNABLE_TYPE,
//                        classes = WebSecurityConfig.class
//                )
//        }
)
class TodoCommentMvcTest {

    private MockMvc mvc;
    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    TodoService todoService;

    @MockBean
    CommentService commentService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String username = "test1";
        String password = "00000000";
        User testUser = new User(username, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    void 로그인() throws Exception {
        // when - then
        mvc.perform(get("/api/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }


    @Test
    void 신규할일등록() throws Exception {
        // given
        this.mockUserSetup();

        String title = "hi";
        String content = "me";
        String maker = "who";

        TodoRequestDto requestDto = new TodoRequestDto(
                title,
                content,
                maker
        );

        String postInfo = objectMapper.writeValueAsString(requestDto);

        // when - then
        mvc.perform(post("/api/todo")
                        .content(postInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 할일업데이트() throws Exception {
        // given
        this.mockUserSetup();

        String todoId = "1";
        String title = "hi";
        String content = "me";

        TodoUpdateRequestDto todoUpdateRequestDto = new TodoUpdateRequestDto();
        todoUpdateRequestDto.setTitle(title);
        todoUpdateRequestDto.setContent(content);

        String putInfo = objectMapper.writeValueAsString(todoUpdateRequestDto);

        // when - then
        mvc.perform(put("/api/todo/"+todoId)
                        .content(putInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    //리턴값 없이 테스트 하는 법
    @Test
    void 할일목록가져오기() throws Exception {
        // given
        this.mockUserSetup();

        // when - then
        mvc.perform(get("/api/todos")
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$..['title']").exists())// jsonPath에 title이 존재하는지. 여기서는 리스트로 반환하기 때문에 안쓰임
                //.andExpect(jsonPath("$.[?(@.title == 'hi')]").exists()) // jsonPath 받아온 리스트 중에서 해당 값이 존재하는지
                .andExpect(jsonPath("$[0]").doesNotExist())


                //위를 포함해서 exists())가 안되는 이유는 Mock 가짜 객체이기 때문
                //고로 리턴값을 확인할 순 없고 연결만 테스트하는 상황


                .andDo(print());
    }

    //리턴값을 이용하여 테스트 하는 법
    @Test
    void 하나의할일목록가져오기() throws Exception {
        // given
        this.mockUserSetup();

        // when - then
        MvcResult mvcResult = mvc.perform(get("/api/todo/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print())
                //.andExpect(jsonPath("$.[?(@.title == 'hi')]").doesNotExist())
                .andReturn(); // jsonPath 받아온 리스트 중에서 해당 값이 존재하는지

        // response 데이터 변환
//        String response = mvcResult.getResponse().getContentAsString();
//        Map<String, Object> responseMap =
//                new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Map.class);
//        // 검증
//        Assertions.assertEquals(responseMap.get("method"), "GET"); // response method 데이터 검증
    }

    @Test
    void 댓글수정() throws Exception {
        // given
        this.mockUserSetup();

        String commentId = "1";
        String content = "updateCommentController";

        CommentUpdateRequestDto commentUpdateRequestDto = new CommentUpdateRequestDto(
                content
        );

        String putInfo = objectMapper.writeValueAsString(commentUpdateRequestDto);

        // when - then
        mvc.perform(put("/api/comment/"+commentId)
                        .content(putInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
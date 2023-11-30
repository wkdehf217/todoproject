package com.todoproject.todoproject.entity;


import com.todoproject.todoproject.dto.todo.TodoRequestDto;
import com.todoproject.todoproject.dto.todo.TodoUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "todo") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String maker;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private Boolean finish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo(TodoRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.maker = requestDto.getMaker();
        this.date = LocalDateTime.now().toString();
        this.finish = false;
        this.user = user;
    }

    public void update(TodoUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void finish() {
        this.finish = true;
    }
}

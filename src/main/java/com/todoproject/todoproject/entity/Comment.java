package com.todoproject.todoproject.entity;

import com.todoproject.todoproject.dto.comment.CommentRequestDto;
import com.todoproject.todoproject.dto.comment.CommentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "comment") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoid", nullable = false)
    private Todo todo;

    public Comment(CommentRequestDto requestDto, Todo todo) {
        this.content = requestDto.getContent();
        this.todo = todo;
    }

    // 트랜잭션 안걸어놓고 헤멤..
    public void update(CommentUpdateRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}



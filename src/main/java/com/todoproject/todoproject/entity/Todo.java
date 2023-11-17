package com.todoproject.todoproject.entity;


import com.todoproject.todoproject.dto.TodoRequestDto;
import com.todoproject.todoproject.dto.TodoTitleContentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private boolean finish;

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

    public void update(TodoTitleContentRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

//    @OneToMany(mappedBy = "product")
//    private List<ProductFolder> productFolderList = new ArrayList<>();

//    public Product(ProductRequestDto requestDto, User user) {
//        this.title = requestDto.getTitle();
//        this.image = requestDto.getImage();
//        this.link = requestDto.getLink();
//        this.lprice = requestDto.getLprice();
//        this.user = user;
//    }
//
//    public void update(ProductMypriceRequestDto requestDto) {
//        this.myprice = requestDto.getMyprice();
//    }

//    public void updateByItemDto(ItemDto itemDto) {
//        this.lprice = itemDto.getLprice();
//    }
}

package com.todoproject.todoproject.repository;

import com.todoproject.todoproject.entity.Comment;
import com.todoproject.todoproject.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentid(Long commentid);
}

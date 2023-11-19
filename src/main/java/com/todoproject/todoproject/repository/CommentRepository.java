package com.todoproject.todoproject.repository;

import com.todoproject.todoproject.entity.Comment;
import com.todoproject.todoproject.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

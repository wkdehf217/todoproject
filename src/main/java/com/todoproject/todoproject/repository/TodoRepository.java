package com.todoproject.todoproject.repository;


import com.todoproject.todoproject.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository <Todo, Long>{
    List<Todo> findAllByOrderByDateDesc();

    Optional<Todo> findBytodoid(Long id);
}

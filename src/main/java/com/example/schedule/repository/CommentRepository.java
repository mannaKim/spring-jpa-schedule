package com.example.schedule.repository;

import com.example.schedule.entity.Comment;
import com.example.schedule.exception.custom.CommentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new CommentNotFoundException(id));
    }
}

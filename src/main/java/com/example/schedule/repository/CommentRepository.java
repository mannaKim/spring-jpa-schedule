package com.example.schedule.repository;

import com.example.schedule.dto.comment.CommentDetailResponseDto;
import com.example.schedule.entity.Comment;
import com.example.schedule.exception.custom.CommentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new CommentNotFoundException(id));
    }

    @Query("SELECT new com.example.schedule.dto.comment.CommentDetailResponseDto(" +
            "c.id, c.contents, m.name, s.title, c.createdAt, c.updatedAt) " +
            "FROM Comment c " +
            "JOIN c.member m " +
            "JOIN c.schedule s " +
            "WHERE (:title IS NULL OR s.title LIKE %:title%) " +
            "AND (:name IS NULL OR m.name LIKE %:name%) "
    )
    Page<CommentDetailResponseDto> findComments(
            @Param("title") String title,
            @Param("name") String name,
            Pageable pageable
    );
}

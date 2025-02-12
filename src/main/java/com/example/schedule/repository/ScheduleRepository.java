package com.example.schedule.repository;

import com.example.schedule.dto.schedule.ScheduleDetailResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.custom.ScheduleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ScheduleNotFoundException(id));
    }

    @Query("SELECT new com.example.schedule.dto.schedule.ScheduleDetailResponseDto(" +
            "s.id, s.title, s.contents, m.name, COUNT(c), s.createdAt, s.updatedAt) " +
            "FROM Schedule s " +
            "JOIN s.member m " +
            "LEFT JOIN Comment c ON c.schedule = s " +
            "GROUP BY s.id, s.title, s.contents, m.name, s.createdAt, s.updatedAt")
    Page<ScheduleDetailResponseDto> findAllWithCommentCount(Pageable pageable);

    @Query("SELECT new com.example.schedule.dto.schedule.ScheduleDetailResponseDto(" +
            "s.id, s.title, s.contents, m.name, COUNT(c), s.createdAt, s.updatedAt) " +
            "FROM Schedule s " +
            "JOIN s.member m " +
            "LEFT JOIN Comment c ON c.schedule = s " +
            "WHERE s.id = :id " +
            "GROUP BY s.id, s.title, s.contents, m.name, s.createdAt, s.updatedAt")
    Optional<ScheduleDetailResponseDto> findByIdWithCommentCount(@Param("id") Long id);
    default ScheduleDetailResponseDto findByIdWithCommentCountOrElseThrow(Long id) {
        return findByIdWithCommentCount(id).orElseThrow(() ->
                new ScheduleNotFoundException(id));
    }
}

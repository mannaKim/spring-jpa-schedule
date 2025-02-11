package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.custom.ScheduleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ScheduleNotFoundException(id));
    }
}

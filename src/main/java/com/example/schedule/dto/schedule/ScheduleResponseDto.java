package com.example.schedule.dto.schedule;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;

    private final String title;

    private final String contents;

    private final Long memberId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getMember().getId(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}

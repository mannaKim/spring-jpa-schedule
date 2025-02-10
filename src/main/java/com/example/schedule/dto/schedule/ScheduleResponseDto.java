package com.example.schedule.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Long id, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

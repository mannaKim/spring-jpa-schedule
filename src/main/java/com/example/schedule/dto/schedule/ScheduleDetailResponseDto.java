package com.example.schedule.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleDetailResponseDto {
    private final Long id;

    private final String title;

    private final String contents;

    private final String memberName;

    private final long commentCount;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
}

package com.example.schedule.dto.schedule;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {
    private final String title;

    private final String contents;
}

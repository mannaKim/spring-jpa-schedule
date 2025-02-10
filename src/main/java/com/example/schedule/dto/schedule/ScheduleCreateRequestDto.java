package com.example.schedule.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    private final String title;

    private final String contents;

    public ScheduleCreateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
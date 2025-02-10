package com.example.schedule.dto.schedule;


import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {

    private final String title;

    private final String contents;

    public ScheduleUpdateRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}

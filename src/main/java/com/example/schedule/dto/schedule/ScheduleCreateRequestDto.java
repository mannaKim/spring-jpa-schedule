package com.example.schedule.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {
    @NotBlank
    @Size(max = 100)
    private final String title;

    @NotBlank
    @Size(max = 500)
    private final String contents;
}
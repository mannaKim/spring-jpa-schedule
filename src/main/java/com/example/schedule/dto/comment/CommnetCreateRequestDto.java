package com.example.schedule.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommnetCreateRequestDto {
    @NotBlank
    @Size(max = 200)
    private final String contents;

    @NotNull
    private final Long scheduleId;
}

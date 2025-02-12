package com.example.schedule.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateRequestDto {
    @NotBlank
    @Size(max = 10)
    private final String name;

    @NotBlank
    @Size(min = 4, max = 20)
    private final String password;
}

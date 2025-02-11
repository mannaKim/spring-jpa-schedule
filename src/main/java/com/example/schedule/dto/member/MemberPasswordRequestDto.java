package com.example.schedule.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberPasswordRequestDto {
    @NotBlank
    @Size(min = 4, max = 20)
    private final String oldPassword;

    @NotBlank
    @Size(min = 4, max = 20)
    private final String newPassword;
}

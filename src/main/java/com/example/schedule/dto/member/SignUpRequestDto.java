package com.example.schedule.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {
    @NotBlank
    @Size(max = 10)
    private final String name;

    @Email
    @NotBlank
    @Size(min = 10, max = 50)
    private final String email;

    @NotBlank
    @Size(min = 4, max = 20)
    private final String password;
}

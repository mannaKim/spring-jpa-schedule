package com.example.schedule.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "Required input is missing: Email")
    private String email;

    @NotBlank(message = "Required input is missing: Password")
    private String password;
}

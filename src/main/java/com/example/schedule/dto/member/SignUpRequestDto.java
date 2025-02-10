package com.example.schedule.dto.member;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String name;

    private final String email;

    public SignUpRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

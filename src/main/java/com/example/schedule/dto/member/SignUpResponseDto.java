package com.example.schedule.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {

    private final Long id;

    private final String name;

    private final String email;
}

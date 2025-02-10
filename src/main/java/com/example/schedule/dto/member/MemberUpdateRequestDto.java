package com.example.schedule.dto.member;

import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    private final String name;

    private final String email;

    public MemberUpdateRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

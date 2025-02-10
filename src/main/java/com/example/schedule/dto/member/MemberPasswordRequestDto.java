package com.example.schedule.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberPasswordRequestDto {

    private final String oldPassword;

    private final String newPassword;
}

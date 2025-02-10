package com.example.schedule.dto.member;

import com.example.schedule.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }
}

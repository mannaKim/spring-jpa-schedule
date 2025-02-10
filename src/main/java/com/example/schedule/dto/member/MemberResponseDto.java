package com.example.schedule.dto.member;

import com.example.schedule.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDto {
    private final Long id;

    private final String name;

    private final String email;

    //private final String password;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public MemberResponseDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

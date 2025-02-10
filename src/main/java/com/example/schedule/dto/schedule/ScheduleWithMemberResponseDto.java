package com.example.schedule.dto.schedule;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleWithMemberResponseDto {
    private final Long id;

    private final String title;

    private final String contents;

    private final Long memberId;

    private final String memberName;

    private final String memberEmail;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static ScheduleWithMemberResponseDto toDto(Schedule schedule) {
        return new ScheduleWithMemberResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getMember().getId(),
                schedule.getMember().getName(),
                schedule.getMember().getEmail(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}

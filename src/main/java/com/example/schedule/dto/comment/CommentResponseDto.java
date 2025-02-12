package com.example.schedule.dto.comment;

import com.example.schedule.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long id;

    private final String contents;

    private final Long memberId;

    private final Long scheduleId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContents(),
                comment.getMember().getId(),
                comment.getSchedule().getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}

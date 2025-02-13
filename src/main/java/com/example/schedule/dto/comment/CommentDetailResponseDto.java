package com.example.schedule.dto.comment;

import com.example.schedule.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDetailResponseDto {
    private final Long id;

    private final String contents;

    private final String memberName;

    private final String scheduleTitle;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static CommentDetailResponseDto toDto(Comment comment) {
        return new CommentDetailResponseDto(
                comment.getId(),
                comment.getContents(),
                comment.getMember().getName(),
                comment.getSchedule().getTitle(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}

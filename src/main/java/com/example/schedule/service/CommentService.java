package com.example.schedule.service;

import com.example.schedule.dto.comment.CommentDetailResponseDto;
import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Member;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.MemberRepository;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(String contents, Long memberId, Long scheduleId) {
        Member findMember = memberRepository.findByIdOrElseThrow(memberId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment comment = new Comment(contents);
        comment.setMember(findMember);
        comment.setSchedule(findSchedule);

        Comment savedComment = commentRepository.save(comment);
        return CommentResponseDto.toDto(savedComment);
    }

    public List<CommentDetailResponseDto> getComments() {

        return commentRepository.findAll()
                .stream()
                .map(CommentDetailResponseDto::toDto)
                .toList();
    }

    public CommentDetailResponseDto getCommentById(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        return CommentDetailResponseDto.toDto(findComment);
    }
}

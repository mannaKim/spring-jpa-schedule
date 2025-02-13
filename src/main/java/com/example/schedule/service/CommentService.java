package com.example.schedule.service;

import com.example.schedule.dto.comment.CommentDetailResponseDto;
import com.example.schedule.dto.comment.CommentResponseDto;
import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Member;
import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.custom.UnauthorizedException;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.MemberRepository;
import com.example.schedule.repository.ScheduleRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final AuthService authService;

    @Transactional
    public CommentResponseDto createComment(String contents, Long scheduleId, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        Member findMember = memberRepository.findByIdOrElseThrow(loggedInMemberId);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        Comment comment = new Comment(contents);
        comment.setMember(findMember);
        comment.setSchedule(findSchedule);

        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDto.toDto(savedComment);
    }

    @Transactional(readOnly = true)
    public Page<CommentDetailResponseDto> getComments(String title, String name, Pageable pageable) {
        return commentRepository.findComments(title, name, pageable);
    }

    @Transactional(readOnly = true)
    public CommentDetailResponseDto getCommentById(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        return CommentDetailResponseDto.toDto(findComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, String contents, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        if (!findComment.getMember().getId().equals(loggedInMemberId)) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        findComment.updateComment(contents);

        Comment updatedComment = commentRepository.findByIdOrElseThrow(id);

        return CommentResponseDto.toDto(updatedComment);
    }

    @Transactional
    public void deleteComment(Long id, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        if (!findComment.getMember().getId().equals(loggedInMemberId)) {
            throw new UnauthorizedException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }

        commentRepository.delete(findComment);
    }
}

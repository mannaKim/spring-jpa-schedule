package com.example.schedule.service;

import com.example.schedule.dto.schedule.ScheduleDetailResponseDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedule.entity.Member;
import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.custom.UnauthorizedException;
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
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final AuthService authService;

    @Transactional
    public ScheduleResponseDto createSchedule(String title, String contents, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        Member findMember = memberRepository.findByIdOrElseThrow(loggedInMemberId);

        Schedule schedule = new Schedule(title, contents);
        schedule.setMember(findMember);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.toDto(savedSchedule);
    }

    @Transactional(readOnly = true)
    public Page<ScheduleDetailResponseDto> getSchedules(String title, String name, String updatedAt, Pageable pageable) {
        return scheduleRepository.findAllWithCommentCount(
                title,
                name,
                updatedAt,
                pageable
        );
    }

    @Transactional(readOnly = true)
    public ScheduleDetailResponseDto getScheduleById(Long id) {
        return scheduleRepository.findByIdWithCommentCountOrElseThrow(id);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto requestDto, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if (!findSchedule.getMember().getId().equals(loggedInMemberId)) {
            throw new UnauthorizedException("본인이 작성한 일정만 수정할 수 있습니다.");
        }

        findSchedule.updateSchedule(requestDto.getTitle(), requestDto.getContents());

        Schedule updatedSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return ScheduleResponseDto.toDto(updatedSchedule);
    }

    @Transactional
    public void deleteSchedule(Long id, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if (!findSchedule.getMember().getId().equals(loggedInMemberId)) {
            throw new UnauthorizedException("본인이 작성한 일정만 삭제할 수 있습니다.");
        }

        scheduleRepository.delete(findSchedule);
    }
}

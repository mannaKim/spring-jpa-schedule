package com.example.schedule.controller;

import com.example.schedule.dto.common.PaginationResponse;
import com.example.schedule.dto.schedule.ScheduleCreateRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedule.dto.schedule.ScheduleWithMemberResponseDto;
import com.example.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleCreateRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getMemberId()
        );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<ScheduleWithMemberResponseDto>> getSchedules(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Page<ScheduleWithMemberResponseDto> schedules = scheduleService.getSchedules(pageable);
        PaginationResponse<ScheduleWithMemberResponseDto> schedulePage = new PaginationResponse<>(schedules);
        return new ResponseEntity<>(schedulePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithMemberResponseDto> getScheduleById(@PathVariable Long id) {
        ScheduleWithMemberResponseDto scheduleWithMemberResponseDto = scheduleService.getScheduleById(id);

        return new ResponseEntity<>(scheduleWithMemberResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

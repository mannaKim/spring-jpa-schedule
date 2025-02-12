package com.example.schedule.controller;

import com.example.schedule.dto.common.PaginationResponse;
import com.example.schedule.dto.schedule.ScheduleCreateRequestDto;
import com.example.schedule.dto.schedule.ScheduleDetailResponseDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<PaginationResponse<ScheduleDetailResponseDto>> getSchedules(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String updatedAt,
            @PageableDefault(size = 10, page = 0, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ScheduleDetailResponseDto> schedulePage = scheduleService.getSchedules(
                title,
                name,
                updatedAt,
                pageable
        );
        PaginationResponse<ScheduleDetailResponseDto> schedulePageResponse = new PaginationResponse<>(schedulePage);
        return new ResponseEntity<>(schedulePageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDetailResponseDto> getScheduleById(@PathVariable Long id) {
        ScheduleDetailResponseDto scheduleDetailResponseDto = scheduleService.getScheduleById(id);

        return new ResponseEntity<>(scheduleDetailResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto,
            HttpSession session
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto, session);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpSession session) {
        scheduleService.deleteSchedule(id, session);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

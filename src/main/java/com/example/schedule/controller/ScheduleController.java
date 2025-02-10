package com.example.schedule.controller;

import com.example.schedule.dto.schedule.ScheduleCreateRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchdule(
                requestDto.getTitle(),
                requestDto.getContents()
        );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }
}

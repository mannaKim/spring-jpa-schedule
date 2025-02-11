package com.example.schedule.exception.custom;

import com.example.schedule.exception.code.ErrorCode;

public class ScheduleNotFoundException extends BaseException {
    public ScheduleNotFoundException(Long id) {
        super(ErrorCode.SCHEDULE_NOT_FOUND_BY_ID, "해당 ID의 일정을 찾을 수 없습니다. id = " + id);
    }
}

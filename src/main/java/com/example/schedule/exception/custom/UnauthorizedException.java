package com.example.schedule.exception.custom;

import com.example.schedule.exception.code.ErrorCode;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(ErrorCode.UNAUTHORIZED, message);
    }
}

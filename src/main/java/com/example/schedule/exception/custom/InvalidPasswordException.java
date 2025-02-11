package com.example.schedule.exception.custom;

import com.example.schedule.exception.code.ErrorCode;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}

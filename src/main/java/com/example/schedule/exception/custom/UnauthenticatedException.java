package com.example.schedule.exception.custom;

import com.example.schedule.exception.code.ErrorCode;

public class UnauthenticatedException extends BaseException {
    public UnauthenticatedException() {
        super(ErrorCode.UNAUTHENTICATED);
    }
}

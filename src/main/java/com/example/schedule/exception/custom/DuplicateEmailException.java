package com.example.schedule.exception.custom;

import com.example.schedule.exception.code.ErrorCode;

public class DuplicateEmailException extends BaseException {
    public DuplicateEmailException(String email) {
        super(ErrorCode.DUPLICATE_EMAIL, "이미 존재하는 이메일입니다. Email = " + email);
    }
}

package com.example.schedule.exception.custom;

import com.example.schedule.exception.code.ErrorCode;

public class MemberNotFoundException extends BaseException {
    public MemberNotFoundException(Long id) {
        super(ErrorCode.MEMBER_NOT_FOUND_BY_ID, "해당 ID의 멤버를 찾을 수 없습니다. ID = " + id);
    }

    public MemberNotFoundException(String email) {
        super(ErrorCode.MEMBER_NOT_FOUND_BY_EMAIL, "해당 이메일의 멤버를 찾을 수 없습니다. Email = " + email);
    }
}

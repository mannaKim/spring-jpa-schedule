package com.example.schedule.exception.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),

    MEMBER_NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND, "해당 ID의 멤버를 찾을 수 없습니다."),

    MEMBER_NOT_FOUND_BY_EMAIL(HttpStatus.NOT_FOUND, "해당 이메일의 멤버를 찾을 수 없습니다."),

    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),

    SCHEDULE_NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND, "해당 ID의 일정을 찾을 수 없습니다."),

    COMMENT_NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND, "해당 ID의 댓글을 찾을 수 없습니다."),

    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),

    UNAUTHORIZED(HttpStatus.FORBIDDEN, "이 작업을 수행할 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}

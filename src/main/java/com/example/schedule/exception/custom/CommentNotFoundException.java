package com.example.schedule.exception.custom;

import com.example.schedule.exception.code.ErrorCode;

public class CommentNotFoundException extends BaseException {
    public CommentNotFoundException(Long id) {
        super(ErrorCode.COMMENT_NOT_FOUND_BY_ID, "해당 ID의 댓글을 찾을 수 없습니다. id = " + id);
    }
}

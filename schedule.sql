CREATE TABLE member
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '작성자 식별자',
    name        VARCHAR(100) NOT NULL COMMENT '유저명',
    email       VARCHAR(100) NOT NULL COMMENT '이메일',
    created_at  DATETIME COMMENT '작성일',
    updated_at  DATETIME COMMENT '수정일'
);

CREATE TABLE schedule
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    member_id   BIGINT COMMENT '멤버 식별자',
    title       VARCHAR(200) NOT NULL COMMENT '할일 제목',
    contents    VARCHAR(200) NOT NULL COMMENT '할일 내용',
    created_at  DATETIME COMMENT '작성일',
    updated_at  DATETIME COMMENT '수정일',
    FOREIGN KEY (member_id) REFERENCES member(id)
);
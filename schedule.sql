CREATE TABLE member
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '작성자 식별자',
    name        VARCHAR(100) NOT NULL COMMENT '작성자명',
    email       VARCHAR(100) NOT NULL COMMENT '이메일',
    created_at  DATETIME COMMENT '등록일',
    updated_at  DATETIME COMMENT '수정일'
);

CREATE TABLE schedule
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    author_id   BIGINT COMMENT '작성자 식별자',
    task        VARCHAR(200) NOT NULL COMMENT '할일',
    password    VARCHAR(100) NOT NULL COMMENT '비밀번호',
    created_at  DATETIME COMMENT '작성일',
    updated_at  DATETIME COMMENT '수정일',
    FOREIGN KEY (author_id) REFERENCES member(id)
);
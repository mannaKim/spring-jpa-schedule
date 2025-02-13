CREATE TABLE member
(
    id         BIGINT      NOT NULL AUTO_INCREMENT,
    name       VARCHAR(10) NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(60) NOT NULL,
    created_at DATETIME(6) NULL,
    updated_at DATETIME(6) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE schedule
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    member_id  BIGINT       NULL,
    title      VARCHAR(100) NOT NULL,
    contents   VARCHAR(500) NOT NULL,
    created_at DATETIME(6)  NULL,
    updated_at DATETIME(6)  NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE
);

CREATE TABLE comment
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    member_id   BIGINT       NULL,
    schedule_id BIGINT       NULL,
    contents    VARCHAR(200) NOT NULL,
    created_at  DATETIME(6)  NULL,
    updated_at  DATETIME(6)  NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member (id) ON DELETE CASCADE,
    FOREIGN KEY (schedule_id) REFERENCES schedule (id) ON DELETE CASCADE
);
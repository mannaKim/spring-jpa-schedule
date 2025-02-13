# 📅 Spring JPA Schedule

---

## 📚 Project Overview

Spring Data JPA를 활용한 일정 관리 애플리케이션입니다.

- **목적**: CRUD 필수 기능을 데이터베이스 연결 및 JPA를 사용해서 개발
- **핵심 기능**:
    - 일정 / 유저 / 댓글 CRUD
    - 회원가입 / 로그인 (Cookie/Session을 활용한 인증/인가)

---

## 📝 Table of Contents

1. [Project Structure](#-project-structure)
2. [ERD](#-erd)
3. [API Documentation](#-api-documentation)
4. [Features](#-features)
5. [Tech Stack](#-tech-stack)

---

## 🛠 Project Structure

```
src
└── main
    ├── java
    │   └── com
    │       └── example
    │           └── schedule
    │               ├── common
    │               ├── config
    │               ├── controller
    │               ├── dto
    │               ├── entity
    │               ├── exception
    │               ├── filter
    │               ├── repository
    │               ├── service
    │               └── SpringJpaScheduleApplication.java
    ├── resources
        ├── application.properties
        
build.gradle
README.md
schedule.sql
```

---

## 🖇️ ERD

![Image](https://github.com/user-attachments/assets/347f8747-2771-4963-996d-893968e1558e)

---

## 📜 API Documentation
### 로그인 관리 API
![Image](https://github.com/user-attachments/assets/b3034fc6-1798-4528-8fb2-6a45395bccf0)

### 유저 관리 API
![Image](https://github.com/user-attachments/assets/9d367526-a446-4df4-abbe-47237d070b52)

### 일정 관리 API
![Image](https://github.com/user-attachments/assets/1591b3a0-4c52-42f1-aac9-ab473626d3e3)

### 댓글 관리 API
![Image](https://github.com/user-attachments/assets/d2be26fb-a398-4546-b5f3-f5ef9e385750)

---

## ✨ Features

### **Lv 1: 일정 CRUD**

- 일정 생성, 조회, 수정, 삭제 기능
- 작성일, 수정일: JPA Auditing 적용

### **Lv 2: 유저 CRUD**

- 유저 생성, 조회, 수정, 삭제 기능
- 작성일, 수정일: JPA Auditing 적용
- 일정-유저 관계 매핑

### **Lv 3: 회원가입**

- 유저에 비밀번호 필드 추가

### **Lv 4: 로그인(인증)**

- Cookie/Session을 활용한 로그인 기능
- 필터를 활용한 인증 처리
- 이메일, 비밀번호 기반 로그인
- 인증 실패 시 401 반환

### **Lv 5: 다양한 예외처리 적용하기**

- Validation 및 커스텀 예외처리 적용

### **Lv 6: 비밀번호 암호화**

- 회원가입 시 비밀번호 암호화 저장
- PasswordEncoder 활용

### **Lv 7: 댓글 CRUD**

- 일정에 댓글 작성, 조회, 수정, 삭제 기능
- 댓글-일정, 댓글-유저 관계 설정
- JPA Auditing 적용

### **Lv 8: 일정 페이징 조회**

- Spring Data JPA의 Pageable, Page 활용한 페이징 기능
- 수정일 기준 내림차순 정렬

---

## 🧑‍💻 Tech Stack

- **Backend**: Spring Framework, Spring Data JPA
- **Database**: MySQL
- **Build Tool**: Gradle


package com.example.schedule.controller;

import com.example.schedule.dto.auth.LoginRequestDto;
import com.example.schedule.dto.auth.LoginResponseDto;
import com.example.schedule.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto requestDto,
            HttpSession session
    ) {
        LoginResponseDto loginResponseDto = authService.login(
                requestDto.getEmail(),
                requestDto.getPassword(),
                session
        );
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        authService.logout(session);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

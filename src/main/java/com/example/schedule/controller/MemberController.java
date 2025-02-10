package com.example.schedule.controller;

import com.example.schedule.dto.member.MemberResponseDto;
import com.example.schedule.dto.member.SignUpRequestDto;
import com.example.schedule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        MemberResponseDto memberResponseDto = memberService.signUp(requestDto.getName(), requestDto.getEmail());

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

}

package com.example.schedule.controller;

import com.example.schedule.dto.member.MemberResponseDto;
import com.example.schedule.dto.member.MemberUpdateRequestDto;
import com.example.schedule.dto.member.SignUpRequestDto;
import com.example.schedule.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembers() {

        List<MemberResponseDto> memberResponseDtoList = memberService.getMembers();

        return new ResponseEntity<>(memberResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id) {

        MemberResponseDto memberResponseDto = memberService.getMemberById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Long id,
            @RequestBody MemberUpdateRequestDto requestDto
    ) {

        MemberResponseDto memberResponseDto = memberService.updateMember(id, requestDto);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

}

package com.example.schedule.controller;

import com.example.schedule.dto.member.*;
import com.example.schedule.service.MemberService;
import jakarta.validation.Valid;
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
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(
                requestDto.getName(), requestDto.getEmail(), requestDto.getPassword()
        );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
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

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody MemberPasswordRequestDto requestDto
    ) {
        memberService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

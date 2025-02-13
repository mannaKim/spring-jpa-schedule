package com.example.schedule.controller;

import com.example.schedule.dto.common.PaginationResponse;
import com.example.schedule.dto.member.*;
import com.example.schedule.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<MemberResponseDto>> getMembers(
            @PageableDefault(size = 10, page = 0, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<MemberResponseDto> memberPage = memberService.getMembers(pageable);
        PaginationResponse<MemberResponseDto> memberPageResponse = new PaginationResponse<>(memberPage);

        return new ResponseEntity<>(memberPageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.getMemberById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<MemberResponseDto> updateMember(
            @Valid @RequestBody MemberUpdateRequestDto requestDto,
            HttpSession session
    ) {
        MemberResponseDto memberResponseDto = memberService.updateMember(requestDto, session);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(
            @Valid @RequestBody MemberPasswordRequestDto requestDto,
            HttpSession session
    ) {
        memberService.updatePassword(
                requestDto.getOldPassword(),
                requestDto.getNewPassword(),
                session
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMember(HttpSession session) {
        memberService.deleteMember(session);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.example.schedule.service;

import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.dto.member.MemberResponseDto;
import com.example.schedule.dto.member.MemberUpdateRequestDto;
import com.example.schedule.dto.member.SignUpResponseDto;
import com.example.schedule.entity.Member;
import com.example.schedule.exception.custom.DuplicateEmailException;
import com.example.schedule.exception.custom.InvalidPasswordException;
import com.example.schedule.exception.custom.UnauthorizedException;
import com.example.schedule.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public SignUpResponseDto signUp(String name, String email, String password) {
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(email);
        }
        String encodePassword = passwordEncoder.encode(password);
        Member member = new Member(name, email, encodePassword);

        Member savedMember = memberRepository.save(member);
        return new SignUpResponseDto(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getEmail()
        );
    }

    public Page<MemberResponseDto> getMembers(Pageable pageable) {

        return memberRepository.findAll(pageable)
                .map(MemberResponseDto::toDto);
    }

    public MemberResponseDto getMemberById(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        return MemberResponseDto.toDto(findMember);
    }

    @Transactional
    public MemberResponseDto updateMember(Long id, MemberUpdateRequestDto requestDto, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        if (!loggedInMemberId.equals(id)) {
            throw new UnauthorizedException("본인만 수정할 수 있습니다.");
        }

        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if (!passwordEncoder.matches(requestDto.getPassword(), findMember.getPassword())) {
            throw new InvalidPasswordException();
        }

        findMember.updateMember(requestDto.getName());
        Member updatedMember = memberRepository.findByIdOrElseThrow(id);
        return MemberResponseDto.toDto(updatedMember);
    }

    public void deleteMember(Long id, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        if (!loggedInMemberId.equals(id)) {
            throw new UnauthorizedException("본인만 삭제할 수 있습니다.");
        }
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        memberRepository.delete(findMember);
    }

    public void updatePassword(Long id, String oldPassword, String newPassword, HttpSession session) {
        Long loggedInMemberId = authService.getLoggedInMemberId(session);
        if (!loggedInMemberId.equals(id)) {
            throw new UnauthorizedException("본인만 수정할 수 있습니다.");
        }

        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if (!passwordEncoder.matches(oldPassword, findMember.getPassword())) {
            throw new InvalidPasswordException();
        }
        findMember.updatePassword(newPassword);
    }
}

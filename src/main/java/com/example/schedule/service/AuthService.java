package com.example.schedule.service;

import com.example.schedule.common.Const;
import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.dto.auth.LoginResponseDto;
import com.example.schedule.entity.Member;
import com.example.schedule.exception.custom.InvalidPasswordException;
import com.example.schedule.exception.custom.UnauthenticatedException;
import com.example.schedule.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(String email, String password, HttpSession session) {
        Member loginMember = memberRepository.findMemberByEmailOrElseThrow(email);

        if (!passwordEncoder.matches(password, loginMember.getPassword())) {
            throw new InvalidPasswordException();
        }

        session.setAttribute(Const.LOGIN_MEMBER, loginMember.getId());

        return new LoginResponseDto(
                loginMember.getId(), loginMember.getName(), loginMember.getEmail()
        );
    }

    public void logout(HttpSession session) {
        if(session != null) {
            session.invalidate();
        }
    }

    public Long getLoggedInMemberId(HttpSession session) {
        Long memberId = (Long) session.getAttribute(Const.LOGIN_MEMBER);
        if (memberId == null) {
            throw new UnauthenticatedException();
        }
        return memberId;
    }
}

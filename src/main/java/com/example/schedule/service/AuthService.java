package com.example.schedule.service;

import com.example.schedule.common.Const;
import com.example.schedule.dto.auth.LoginResponseDto;
import com.example.schedule.entity.Member;
import com.example.schedule.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public LoginResponseDto login(String email, String password, HttpSession session) {
        Member loginMember = memberRepository.findMemberByEmailOrElseThrow(email);

        if (!loginMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is incorrect.");
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
}

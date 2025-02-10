package com.example.schedule.service;

import com.example.schedule.dto.member.MemberResponseDto;
import com.example.schedule.dto.member.MemberUpdateRequestDto;
import com.example.schedule.entity.Member;
import com.example.schedule.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto signUp(String name, String email) {
        Member member = new Member(name, email);

        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getEmail(),
                savedMember.getCreatedAt(),
                savedMember.getUpdatedAt()
        );
    }

    public List<MemberResponseDto> getMembers() {

        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();
    }

    public MemberResponseDto getMemberById(Long id) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        return MemberResponseDto.toDto(findMember);
    }

    @Transactional
    public MemberResponseDto updateMember(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findByIdOrElseThrow(id);

        member.updateMember(requestDto.getName(), requestDto.getEmail());

        Member updatedMember = memberRepository.findByIdOrElseThrow(id);

        return MemberResponseDto.toDto(updatedMember);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findByIdOrElseThrow(id);

        memberRepository.delete(member);
    }
}

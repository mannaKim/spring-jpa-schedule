package com.example.schedule.repository;

import com.example.schedule.entity.Member;
import com.example.schedule.exception.custom.MemberNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new MemberNotFoundException(id));
    }

    Optional<Member> findMemberByEmail(String email);
    default Member findMemberByEmailOrElseThrow(String email) {
        return findMemberByEmail(email).orElseThrow(() ->
                new MemberNotFoundException(email));
    }

    boolean existsByEmail(String email);
}

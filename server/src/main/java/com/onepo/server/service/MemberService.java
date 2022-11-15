package com.onepo.server.service;


import com.onepo.server.domain.member.Member;
import com.onepo.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    public Long join(Member member) {
        validateDuplicateUserId(member);    //아이디 중복 검사
        memberRepository.save(member);
        return member.getId();
    }

    //아이디 중복 검사
    private void validateDuplicateUserId(Member member) {
        List<Member> members = memberRepository.findByUserId(member.getUserId());
        if (!members.isEmpty()) {
            throw new IllegalStateException("동일한 아이디가 이미 존재합니다.");
        }
    }

    //회원 단건 조회
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    //전체 회원 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findByUserId(String userId) {
        List<Member> users = memberRepository.findByUserId(userId);
        return users.get(0);
    }

    public Member authenticated(Member member, String password) {
        if (member.getPassword().equals(password)) {
            return member;
        }
        return null;
    }
}

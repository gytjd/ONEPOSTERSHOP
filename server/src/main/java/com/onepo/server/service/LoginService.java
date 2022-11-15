package com.onepo.server.service;

import com.onepo.server.domain.member.Member;
import com.onepo.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final MemberRepository memberRepository;

    /**
     * 로그인 아이디 또는 비밀번호 일치 여부 확인
     * 불일치 => null, 일치 => Member 객체 반환으로 로그인 success/fail 여부 판단
     */
    public Member authenticated(String userId, String password) {
        List<Member> findMembers = memberRepository.findByUserId(userId);
        if (findMembers.isEmpty()) {
            return null;
        }
        Member member = findMembers.get(0);
        if (!(member.getPassword().equals(password))) {
            return null;
        }
        return member;
    }
}

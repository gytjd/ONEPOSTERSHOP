package com.onepo.server.service;

import com.onepo.server.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private LoginService loginService;

    @Test
    @Rollback(value = false)
    public void 로그인() throws Exception {
        Member member1 = new Member();
        member1.register("taegon", "taegon1998", "123", "taegon1998@gmail.com");
        Member member2 = new Member();
        member2.register("taegon", "taegon1999", "456", "taegon1998@gmail.com");
        memberService.join(member1);
        memberService.join(member2);

        Member findMember1 = memberService.findOne(member1.getId());
        Member findMember2 = memberService.findOne(member2.getId());
        //1번 유저아이디와 2번 유저 비밀번호 authenticated => null(실패값이 반환 되어야함)
        Member resultMember = loginService.authenticated(findMember1.getUserId(), findMember2.getPassword());

        Assertions.assertThat(resultMember).isNull();   //아이디 또는 비밀번호 불일치 => return null값
    }

}
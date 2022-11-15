package com.onepo.server.service;

import com.onepo.server.domain.member.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.register("taegon", "taegon1998", "123", "taegon1998@gmail.com");

        Long id = memberService.join(member);
        Member findMember = memberService.findOne(id);

        assertThat(member).isEqualTo(findMember);
    }

    @Test(expected = IllegalStateException.class)
    public void 중복아이디검사() throws Exception {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.register("taegon", "taegon1999", "123", "taegon1998@gmail.com");
        member2.register("taegon", "taegon1999", "123", "taegon1998@gmail.com");

        memberService.join(member1);
        memberService.join(member2);    //예외 발생 시점

        Assert.fail("예외 발생");
    }
}
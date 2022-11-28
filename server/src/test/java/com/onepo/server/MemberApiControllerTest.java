package com.onepo.server;

import com.onepo.server.domain.member.Member;
import com.onepo.server.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberApiControllerTest {

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        Member member=new Member();
    }
}

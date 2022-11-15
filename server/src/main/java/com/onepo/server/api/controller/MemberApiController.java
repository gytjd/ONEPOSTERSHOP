package com.onepo.server.api.controller;

import com.onepo.server.api.dto.member.MemberCreateDto;
import com.onepo.server.api.dto.member.MemberCreateResponse;
import com.onepo.server.api.dto.member.MemberLoginDto;
import com.onepo.server.api.dto.member.MemberLoginResponse;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.member.SessionConst;
import com.onepo.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/createMember")
    public MemberCreateDto createMember() {
        return new MemberCreateDto();
    }

    @PostMapping("/createMember")
    public MemberCreateResponse create(@Validated @RequestBody MemberCreateDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return null;
        }
        Member member = new Member();

        member.register(dto.getName(), dto.getUserId(), dto.getPassword(), dto.getEmail());
        memberService.join(member);

        return new MemberCreateResponse(member);
    }

    @GetMapping("/login")
    public MemberLoginDto loginForm() {
        return new MemberLoginDto();
    }
    @PostMapping("/login")
    public MemberLoginResponse login(@Validated @RequestBody MemberLoginDto dto, BindingResult bindingResult, HttpServletRequest request) {

        String findUserId = dto.getUserId();
        Member findMember = memberService.findByUserId(findUserId);
        String findPassword = dto.getPassword();

        Member member = memberService.authenticated(findMember, findPassword);

        if (member.equals(null) || bindingResult.hasErrors()) {
            return null;
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return new MemberLoginResponse(member);
    }

    /*@PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }*/
}

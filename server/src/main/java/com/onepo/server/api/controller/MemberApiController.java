package com.onepo.server.api.controller;

import com.onepo.server.api.dto.member.MemberCreateDto;
import com.onepo.server.api.dto.member.MemberLoginDto;
import com.onepo.server.api.dto.member.MemberLoginResponse;
import com.onepo.server.api.dto.member.PasswordForm;
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
@CrossOrigin
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public MemberCreateDto saveMember() {
        return new MemberCreateDto();
    }

    @PostMapping("/member/new")
    public String Save(@Validated @RequestBody MemberCreateDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return null;
        }
        Member member = new Member();

        member.register(dto.getName(), dto.getUserId(), dto.getPassword(), dto.getEmail());
        memberService.join(member);

        return "redirect:/login";
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

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping("/member/pwdChange")
    public PasswordForm changePassword() {
        return new PasswordForm();
    }

    @PutMapping("/member/pwdChange/{id}")
    public String change(@PathVariable("id") Long id,
                         @Validated @RequestBody PasswordForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return null;
        }
        memberService.updateMember(id, form);
        return "success";
    }
}

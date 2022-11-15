package com.onepo.server.api.controller;

import com.onepo.server.api.dto.member.MemberCreateDto;
import com.onepo.server.api.dto.member.MemberCreateResponse;
import com.onepo.server.domain.member.Member;
import com.onepo.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public MemberCreateResponse create(@RequestBody MemberCreateDto dto) {
        Member member = new Member();

        member.register(dto.getName(), dto.getUserId(), dto.getPassword(), dto.getEmail());
        memberService.join(member);

        return new MemberCreateResponse(member);
    }
}

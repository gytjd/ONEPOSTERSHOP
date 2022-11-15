package com.onepo.server.api.dto.member;

import com.onepo.server.domain.member.Member;
import lombok.Data;

@Data
public class MemberLoginResponse {

    private String name;

    public MemberLoginResponse(Member member) {
        this.name = member.getName();
    }
}

package com.onepo.server.api.dto.member;

import com.onepo.server.domain.member.Member;
import lombok.Data;
@Data
public class MemberCreateResponse {
    private String name;
    public MemberCreateResponse(Member member) {
        this.name = member.getName();
    }
}

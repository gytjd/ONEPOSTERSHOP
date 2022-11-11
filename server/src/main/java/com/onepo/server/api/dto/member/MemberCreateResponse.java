package com.onepo.server.api.dto.member;

import com.onepo.server.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MemberCreateResponse {
    private String name;
    private String userId;
    private String email;

    public MemberCreateResponse(Member member) {
        this.name = member.getName();
        this.userId = member.getUserId();
    }
}

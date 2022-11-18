package com.onepo.server.api.dto.member;

import lombok.Data;

@Data
public class MemberLoginResponse {

    private String userId;
    private String name;

    public MemberLoginResponse(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

}

package com.onepo.server.api.dto.member;

import lombok.Data;

@Data
public class MemberLoginResponse {

    private String userId;
    private String name;

    private String token;

    public MemberLoginResponse(String userId, String name, String token) {
        this.userId = userId;
        this.name = name;
        this.token = token;
    }

}

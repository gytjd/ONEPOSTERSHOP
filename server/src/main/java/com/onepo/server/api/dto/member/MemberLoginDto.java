package com.onepo.server.api.dto.member;

import lombok.Data;

@Data
public class MemberLoginDto {

    private String userId;

    private String password;
}

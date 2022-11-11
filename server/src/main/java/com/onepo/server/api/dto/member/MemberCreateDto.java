package com.onepo.server.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberCreateDto {

    private String name;
    private String userId;
    private String password;
    private String email;

}

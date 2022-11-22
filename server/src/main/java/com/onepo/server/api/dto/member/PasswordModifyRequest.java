package com.onepo.server.api.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordModifyRequest {

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    private String newPassword;

    @NotBlank(message = "입력하신 비밀번호를 확인해주세요.")
    private String newPasswordConfirm;
}

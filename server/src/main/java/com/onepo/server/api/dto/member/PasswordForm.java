package com.onepo.server.api.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
public class PasswordForm {

    @Length(min = 8, max = 15)
    private String newPassword;

    @Length(min = 8, max = 15)
    private String newPasswordConfirm;
}

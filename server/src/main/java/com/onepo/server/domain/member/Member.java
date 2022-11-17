package com.onepo.server.domain.member;

import com.onepo.server.api.dto.member.PasswordForm;
import com.onepo.server.domain.wish.Wish;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String userId;

    @NotEmpty
//    @Length(min = 8, max = 15)
    @Setter
    private String password;

    @NotEmpty
    @Email
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="WISH_ID")
    private Wish wish;

    //회원등록 생성자
    public void register(String name, String userId, String password, String email) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public void passwordChange(String newPassword) {
        this.password = newPassword;
    }
}

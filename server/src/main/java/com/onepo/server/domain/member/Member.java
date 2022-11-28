package com.onepo.server.domain.member;

import com.onepo.server.domain.wish.Wish;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String userId;
    private String password;
    @Email
    private String email;

    private String token;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="WISH_ID")
    private Wish wish;

    //회원등록 생성자
    public void register(String name, String userId, String password, String email) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.grade = Grade.USER;
        this.token = UUID.randomUUID().toString();
    }

    public void passwordChange(String newPassword) {
        this.password = newPassword;
    }
}

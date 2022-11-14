package com.onepo.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

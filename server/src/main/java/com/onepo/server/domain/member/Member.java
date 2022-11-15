package com.onepo.server.domain.member;

import com.onepo.server.domain.wish.Wish;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

<<<<<<< HEAD
=======
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="WISH_ID")
    //private Wish wish;
>>>>>>> origin/main

    //회원등록 생성자
    public void register(String name, String userId, String password, String email) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }
}

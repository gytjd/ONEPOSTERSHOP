package com.onepo.server.domain.wish;

import com.onepo.server.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Wish {

    @Id
    @GeneratedValue
    @Column(name="WISH_ID")
    private Long id;

    //@OneToOne(mappedBy = "wish",fetch = FetchType.LAZY)
    //private Member member;

}

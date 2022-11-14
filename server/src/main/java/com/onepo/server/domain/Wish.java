package com.onepo.server.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Wish {

    @Id
    @GeneratedValue
    @Column(name="WISH_ID")
    private Long id;

    @OneToOne(mappedBy = "wish",fetch = FetchType.LAZY)
    private Member member;

}

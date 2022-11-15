package com.onepo.server.domain.wish;

import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.OrderItem;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wish {

    @Id
    @GeneratedValue
    @Column(name="WISH_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    private int count;

    @OneToMany(mappedBy = "wish",cascade = CascadeType.ALL)
    private List<WishItem> wishItemList = new ArrayList<>();


    public Wish(Member member) {
        this.member = member;
    }


    public static Wish createCart(Member member) {
        Wish wish=new Wish(member);
        wish.setCount(0);
        return wish;
    }


}

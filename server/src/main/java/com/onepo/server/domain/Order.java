package com.onepo.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    public Order(Member member, Delivery delivery, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.member = member;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

}

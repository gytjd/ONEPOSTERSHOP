package com.onepo.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Embedded
    private Address address;

    public Order(Member member, LocalDateTime orderDate, OrderStatus orderStatus, Address address,OrderItem...orderItems) {
        this.member = member;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;

        for (OrderItem orderItem: orderItems) {
            this.addOrderItem(orderItem);
        }
    }

    // 편의 메소드

    public void setMember(Member member) {
        this.member=member;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    //생성 메소드

    public static Order createOrder(Member member,Address address,OrderItem...orderItems) {

        Order order=new Order(member,LocalDateTime.now(),OrderStatus.ORDER,address,orderItems);

        return order;
    }

    // 비지니스 로직

}

package com.onepo.server.domain.order;

import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.delivery.DeliveryStatus;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.wish.Wish;
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

    @ManyToOne
    @JoinColumn(name = "WISH_ID")
    private Wish wish;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;


    public Order(Member member, Wish wish,LocalDateTime orderDate, OrderStatus orderStatus, Delivery delivery,List<OrderItem> orderItems) {
        this.member = member;
        this.wish=wish;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.delivery=delivery;

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

    public static Order createOrder(Member member,Wish wish,Delivery delivery,List<OrderItem> orderItems) {

        Order order=new Order(member,wish,LocalDateTime.now(),OrderStatus.ORDER,delivery,orderItems);

        return order;
    }





}

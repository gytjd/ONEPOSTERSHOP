package com.onepo.server.domain.order;

import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.delivery.DeliveryStatus;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.wish.Wish;
import lombok.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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


    @DateTimeFormat(pattern = "yyyy-mm-dd-hh-mm-ss")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;


    // 장바구니 주문

    public Order(Member member,LocalDateTime orderDate, OrderStatus orderStatus, Delivery delivery,List<OrderItem> orderItems) {
        this.member = member;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.delivery=delivery;

        for (OrderItem orderItem: orderItems) {
            this.addOrderItem(orderItem);
        }
    }


    // 편의 메소드

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //생성 메소드


    // 장바구니 주문

    public static Order createOrder(Member member,Delivery delivery,List<OrderItem> orderItem) {

        Order order=new Order(member,LocalDateTime.now(),OrderStatus.ORDER,delivery,orderItem);

        return order;
    }


}

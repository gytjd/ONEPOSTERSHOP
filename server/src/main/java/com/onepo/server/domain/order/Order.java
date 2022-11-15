package com.onepo.server.domain.order;

import com.onepo.server.domain.Delivery;
import com.onepo.server.domain.DeliveryStatus;
import com.onepo.server.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;


    public Order(Member member, LocalDateTime orderDate, OrderStatus orderStatus, Delivery delivery,List<OrderItem> orderItems) {
        this.member = member;
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

    public void setDelivery(Delivery delivery) {
        this.delivery=delivery;
    }


    //생성 메소드

    public static Order createOrder(Member member,Delivery delivery,List<OrderItem> orderItems) {

        Order order=new Order(member,LocalDateTime.now(),OrderStatus.ORDER,delivery,orderItems);

        return order;
    }

    // 비지니스 로직

    public void cancel() {
        if (delivery.getStatus()== DeliveryStatus.COMP) {
            throw new IllegalStateException("배송 된 상품은 취소 불가입니당 ㅋ");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : this.orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        int totalPrice=0;

        for(OrderItem orderItem : this.orderItems) {
            totalPrice+= orderItem.getTotalPrice();
        }
        return totalPrice;
    }


}

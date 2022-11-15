package com.onepo.server.domain.order;

import com.onepo.server.domain.item.Item;
<<<<<<< HEAD
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.wish.WishItem;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

>>>>>>> origin/main
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    @JoinColumn(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ITEM_ID")
    private Item item;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member; // 구매자

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="WISH_ITEM_ID")
    private WishItem wishItem;

    private int orderPrice;
    private int count;

    private int TotalPrice;


    public static OrderItem createOrderItem(Member member,Item item,WishItem wishItem) {
        OrderItem orderItem=new OrderItem();

        orderItem.setMember(member);
        orderItem.setItem(item);
        orderItem.setWishItem(wishItem);
        orderItem.setOrderPrice(wishItem.getItem().getPrice());
        orderItem.setCount(wishItem.getWishCount());
        orderItem.setTotalPrice(wishItem.getItem().getPrice()*wishItem.getWishCount());
=======
    private int orderPrice;
    private int count;

    public OrderItem(Item item, int orderPrice, int count) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    // 생성 메소드

    public static OrderItem createOrderItem(Item item,int orderPrice,int count)
    {
        OrderItem orderItem=new OrderItem(item,orderPrice,count);

        item.removeStock(count);
>>>>>>> origin/main

        return orderItem;
    }

<<<<<<< HEAD


=======
    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
>>>>>>> origin/main
}

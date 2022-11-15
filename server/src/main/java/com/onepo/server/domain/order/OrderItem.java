package com.onepo.server.domain.order;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

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

        return orderItem;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }
}

package com.onepo.server.domain.order;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.wish.WishItem;
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
    private Member member; // 구매자

    private int orderPrice;
    private int count;
    private int TotalPrice;





    // 장바구니 주문
    public static OrderItem createOrderItem(Member member,Item item,WishItem wishItem) {
        OrderItem orderItem = new OrderItem();

        orderItem.setMember(member);
        orderItem.setItem(item);

        orderItem.setOrderPrice(wishItem.getItem().getPrice());
        orderItem.setCount(wishItem.getWishCount());
        orderItem.setTotalPrice(wishItem.getItem().getPrice() * wishItem.getWishCount());

        return orderItem;
    }

    //개별 주문

    public static OrderItem createOrderItem(Member member,Item item,int count) {
        OrderItem orderItem = new OrderItem();

        orderItem.setMember(member);
        orderItem.setItem(item);

        orderItem.setOrderPrice(item.getPrice());
        orderItem.setCount(count);
        orderItem.setTotalPrice(item.getPrice()*count);

        return orderItem;
    }

}

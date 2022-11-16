package com.onepo.server.service;


import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
import com.onepo.server.domain.order.OrderItem;
import com.onepo.server.domain.wish.Wish;
import com.onepo.server.domain.wish.WishItem;
import com.onepo.server.repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;
    private final MemberService memberService;
    private final WishService wishService;


    /**
     *
     * @param userId
     * @param item
     * @param wishItem
     * @return
     * // 장바구니 주문
     */

    @Transactional
    public OrderItem addCartOrder(Long userId, Item item, WishItem wishItem) {
        Member findMember = memberService.findOne(userId);

        OrderItem orderItem = OrderItem.createOrderItem(findMember,item,wishItem);

        save_order_item(orderItem);

        return orderItem;
    }

    @Transactional
    public Long addOrder(Member member, Wish wish, Delivery delivery, List<OrderItem> orderItemList) {
        Order order = Order.createOrder(member,wish, delivery, orderItemList);

        save_order(order);

        return order.getId();
    }

    @Transactional
    public Long order_cart(Member member,Delivery delivery) { // 장바구니 주문
        Wish wishByMemberId = wishService.findWishByMemberId(member.getId());
        List<WishItem> userWishList = wishService.findWishItemsByWishId(wishByMemberId.getId());

        List<OrderItem> orderItemList = new ArrayList<>();

        for (WishItem wishItem : userWishList) {
            OrderItem orderItem = addCartOrder(member.getId(),
                    wishItem.getItem(),
                    wishItem);

            orderItemList.add(orderItem);
        }

        Long orderId = addOrder(member, wishByMemberId,delivery, orderItemList);

        return orderId;
    }


    /**
     *
     *
     * 개별 주문
     */


    @Transactional
    public OrderItem add_OrderOne(Long userId, Item item,int count) {
        Member findMember = memberService.findOne(userId);

        OrderItem orderItem = OrderItem.createOrderItem(findMember,item,count);

        item.removeStock(count);

        save_order_item(orderItem);

        return orderItem;
    }

    @Transactional
    public Long Order_one(Member member,Delivery delivery, List<OrderItem> orderItemList) {
        Order order = Order.createOrder(member, delivery, orderItemList);

        save_order(order);

        return order.getId();
    }


    @Transactional
    public Long order_one(Member member,Delivery delivery,Item item,int count) {
        OrderItem createOrderOne = add_OrderOne(member.getId(), item, count);

        List<OrderItem> orderItem=new ArrayList<>();
        orderItem.add(createOrderOne);

        Long orderId = Order_one(member, delivery, orderItem);

        return orderId;
    }



    // OrderRepository Service

    public void save_order(Order order) {
        orderRepository.save(order);
    }

    // OrderItemRepository Service

    public void save_order_item(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }


}

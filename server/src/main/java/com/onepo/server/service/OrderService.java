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
    private final MemberRepository memberRepository;

    private final WishItemRepository wishItemRepository;

    private final WishRepository wishRepository;

    @Transactional
    public OrderItem addCartOrder(Long userId, Item item, WishItem wishItem) {
        Member findMember = memberRepository.findOne(userId);

        OrderItem orderItem = OrderItem.createOrderItem(findMember,item,wishItem);

        orderItemRepository.save(orderItem);

        return orderItem;
    }

    @Transactional
    public Long addOrder(Member member, Wish wish, Delivery delivery, List<OrderItem> orderItemList) {
        Order order = Order.createOrder(member,wish, delivery, orderItemList);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public Long order(Member member,Delivery delivery) {
        Wish wishByMemberId = wishRepository.findWishByMemberId(member.getId());
        List<WishItem> userWishList = wishItemRepository.findWishItemsByWishId(wishByMemberId.getId());

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


}

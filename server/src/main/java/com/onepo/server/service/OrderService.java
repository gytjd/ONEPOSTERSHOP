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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    private final WishItemRepository wishItemRepository;

    private final WishRepository wishRepository;

    @Transactional
    public Long order(Long memberId,Long itemId,Delivery delivery,int count) {

        Member findMember = memberRepository.findOne(memberId);
        Item findItem = itemRepository.findOne(itemId);


        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);
        Order order = Order.createOrder(findMember,delivery,orderItem);

        orderRepository.save(order);

        return order.getId();
    }


    public List<OrderItem> findUserOrderItems(Long id) {
        return orderItemRepository.findOrderItemsByMemberId(id);
    }

    public OrderItem findOrderItem(Long orderItemid) {
        return orderItemRepository.findOrderItemById(orderItemid);
    }







}

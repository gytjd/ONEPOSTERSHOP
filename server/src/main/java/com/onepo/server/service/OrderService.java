package com.onepo.server.service;


import com.onepo.server.domain.*;
import com.onepo.server.domain.item.Item;
import com.onepo.server.repository.ItemRepository;
import com.onepo.server.repository.MemberRepository;
import com.onepo.server.repository.OrderRepository;
import com.onepo.server.repository.WishRepository;
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
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final WishRepository wishRepository;

    @Transactional
    public Long order(Long memberId,Delivery delivery) {

        Member findMember = memberRepository.findOne(memberId);
        List<OrderItem> orderItems = wishRepository.AllCart();

        Order order = Order.createOrder(findMember,delivery,orderItems);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order findOrder = orderRepository.findOne(orderId);
        findOrder.cancel();
    }

    public OrderItem cart(Long itemId,int count) {
        Item findItem = itemRepository.findOne(itemId);
        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);

        wishRepository.cart(orderItem);

        return orderItem;
    }


}

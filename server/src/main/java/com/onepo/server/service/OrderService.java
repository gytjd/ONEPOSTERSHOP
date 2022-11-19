package com.onepo.server.service;


import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.delivery.DeliveryStatus;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
import com.onepo.server.domain.order.OrderItem;
import com.onepo.server.domain.wish.Wish;
import com.onepo.server.domain.wish.WishItem;
import com.onepo.server.exception.AlreadyDeliveredException;
import com.onepo.server.exception.NotPermitException;
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
    public OrderItem add_Wish_Item(Long userId, Item item, WishItem wishItem) {
        Member findMember = memberService.findOne(userId);

        OrderItem orderItem = OrderItem.createOrderItem(findMember,item,wishItem);

        save_order_item(orderItem);

        return orderItem;
    }

    @Transactional
    public Long add_Wish_Order(Member member,Delivery delivery, List<OrderItem> orderItemList) {
        Order order = Order.createOrder(member,delivery, orderItemList);

        save_order(order);

        return order.getId();
    }

    @Transactional
    public Long order_Wish(Member member, Delivery delivery) { // 장바구니 주문
        Wish wishByMemberId = wishService.findWishByMemberId(member.getId());
        List<WishItem> userWishList = wishService.findWishItemsByWishId(wishByMemberId.getId());

        List<OrderItem> orderItemList = new ArrayList<>();

        for (WishItem wishItem : userWishList) {
            OrderItem orderItem = add_Wish_Item(member.getId(),
                    wishItem.getItem(),
                    wishItem);
            orderItemList.add(orderItem);

            wishItem.getItem().removeStock(wishItem.getWishCount());
        }

        Long orderId = add_Wish_Order(member,delivery, orderItemList);


        delete_All_Wish(wishByMemberId);

        return orderId;

    }


    /**
     *
     *
     * 개별 주문
     */


    @Transactional
    public OrderItem add_One_Item(Long userId, Item item,int count) {
        Member findMember = memberService.findOne(userId);

        OrderItem orderItem = OrderItem.createOrderItem(findMember,item,count);

        item.removeStock(count);

        save_order_item(orderItem);

        return orderItem;
    }

    @Transactional
    public Long add_One_Order(Member member,Delivery delivery, List<OrderItem> orderItemList) {
        Order order = Order.createOrder(member, delivery, orderItemList);

        save_order(order);

        return order.getId();
    }


    @Transactional
    public Long order_One(Member member,Delivery delivery,Item item,int count) {
        OrderItem createOrderOne = add_One_Item(member.getId(), item, count);

        List<OrderItem> orderItem=new ArrayList<>();
        orderItem.add(createOrderOne);

        item.removeStock(createOrderOne.getCount());
        Long orderId = add_One_Order(member, delivery, orderItem);

        return orderId;
    }

    /**
     *
     *
     * 공통 Service
     */

    @Transactional
    public void order_Cancel(Member member,Long orderId) {
        Order order = findOrderByMemberIdAndId(member.getId(), orderId);

        if(order.getMember().getId()!=member.getId()) {
            throw new NotPermitException();
        }

        if(order.getDelivery().getStatus()== DeliveryStatus.COMP) {
            throw new AlreadyDeliveredException();
        }
        else {
            List<OrderItem> orderItems = findOrderItemsByOrderId(order.getId());

            for(OrderItem orderItem : orderItems) {

                Item item = orderItem.getItem();
                item.addStock(orderItem.getCount());

                orderItemRepository.delete(orderItem);
            }

            orderRepository.delete(order);
        }

    }

    public List<Order> order_list(Member member) { // 사용자가 주문한 전체 주문 확인
        return findOrdersByMemberId(member.getId());
    }

    @Transactional
    public void delete_All_Wish(Wish wish) {

        wishService.delete_All_wishItem(wish.getId());
        wishService.delete_Wish(wish);
    }


    /**
     *
      * OrderRepository Service
     */

    public void save_order(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findOrdersByMemberId(Long id) {
        return orderRepository.findOrdersByMemberId(id);
    }

    public Order findOrderByMemberId(Long id) {
        return orderRepository.findOrderByMemberId(id);
    }

    public Order findOrderByMemberIdAndId(Long memberId,Long orderId) {
        return orderRepository.findOrderByMemberIdAndId(memberId,orderId);
    }


    /**
     *
     * OrderItemRepository Service
     */

    public void save_order_item(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public List<OrderItem> findOrderItemsByOrderId(Long id) {
        return orderItemRepository.findOrderItemsByOrderId(id);
    }




}

package com.onepo.server.service;

import com.onepo.server.domain.*;
import com.onepo.server.domain.item.Item;
import com.onepo.server.repository.OrderRepository;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Rollback(value = false)
    public void 상품주문() throws Exception{
        Member member=new Member();
        member.register("황효성","hys3396","1234","hys339631@gmail.com");
        em.persist(member);

        Item item=new Item();
        item.createArt("그림",10,10000,"보기 좋음");
        em.persist(item);

        Delivery delivery=new Delivery();

        Address address=new Address("대구시","달서구","용산동");
        delivery.setAddress(address);

        delivery.setStatus(DeliveryStatus.READY);
        int orderCount=3;

        Long orderId = orderService.order(member.getId(), item.getId(),delivery,orderCount);
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("주문되면 수량은 7로 떨어짐",7,item.getStockQuantity());
        Assert.assertEquals("주문상태는 ORDER 여야함", OrderStatus.ORDER,getOrder.getOrderStatus());
        Assert.assertEquals("주문자의 배송지는 대구시 이여야함",getOrder.getDelivery().getAddress().getCity(),"대구시");
        Assert.assertEquals("그럼 총 가격은 30000 원 이여야함",30000,getOrder.getTotalPrice());
    }


    @Test
    @Rollback(value = false)
    public void 상품취소() throws Exception{
        Member member=new Member();
        member.register("황효성","hys3396","1234","hys339631@gmail.com");
        em.persist(member);

        Item item=new Item();
        item.createArt("그림",10,10000,"보기 좋음");
        em.persist(item);

        Delivery delivery=new Delivery();

        Address address=new Address("대구시","달서구","용산동");
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);

        int orderCount=1;

        Long orderId = orderService.order(member.getId(), item.getId(), delivery, orderCount);
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("현재 수량은 그럼 9",9,item.getStockQuantity());

        orderService.cancelOrder(orderId);

        Assert.assertEquals("현재 수량은 그럼 10",10,item.getStockQuantity());
    }
}
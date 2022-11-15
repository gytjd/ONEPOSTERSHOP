package com.onepo.server.service;

import com.onepo.server.domain.*;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
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
    public void 장바구니에담은상품등록() throws  Exception {
        Member member=new Member();
        member.register("황효성","hys3396","1234","hys339631@gmail.com");
        em.persist(member);

        Item item1=new Item();
        item1.createArt("아이폰",50,10000,"보기 좋음");
        em.persist(item1);

        Item item2=new Item();
        item2.createArt("아이패드",50,20000,"보기 좋음");
        em.persist(item2);

        Item item3=new Item();
        item3.createArt("맥북",50,30000,"보기 좋음");
        em.persist(item3);


        Delivery delivery=new Delivery();
        Address address=new Address("대구시","달서구","용산동");
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);


        orderService.cart(item1.getId(),2);
        orderService.cart(item2.getId(),3);

        Long orderId = orderService.order(member.getId(), delivery);
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("Item1 남은 수량은 48",48,item1.getStockQuantity());
        Assert.assertEquals("Item2 남은 수량은 47",47,item2.getStockQuantity());
        Assert.assertEquals("그럼 주문 물품은 2개",2,getOrder.getOrderItems().size());
        Assert.assertEquals("그럼 주문 가격은 80000",80000,getOrder.getTotalPrice());


        Member member2=new Member();
        member2.register("이태곤","taegon1234","1234","taegon@gmail.com");
        em.persist(member2);

        Delivery delivery2=new Delivery();
        Address address2=new Address("대구시","달서구","용산동");
        delivery2.setAddress(address2);
        delivery2.setStatus(DeliveryStatus.READY);
    }
}
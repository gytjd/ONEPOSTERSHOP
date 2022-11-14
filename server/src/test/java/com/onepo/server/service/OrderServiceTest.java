package com.onepo.server.service;

import com.onepo.server.domain.Member;
import com.onepo.server.domain.Order;
import com.onepo.server.domain.OrderStatus;
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

//    @Test
//    @Rollback(value = false)
//    public void 상품주문() throws Exception{
//        Member member=new Member();
//        member.register("황효성","hys3396","1234","hys339631@gmail.com");
//        em.persist(member);
//
//        Item item=new Item();
//        item.createArt("그림",10,10000,"보기 좋음");
//        em.persist(item);
//
//        Address address=new Address("대구시","달서구","용산동");
//
//        int orderCount=1;
//
//        Long orderId = orderService.order(member.getId(), item.getId(), address,orderCount);
//        Order getOrder = orderRepository.findOne(orderId);
//
//        Assert.assertEquals("주문되면 수량은 9로 떨어짐",9,item.getStockQuantity());
//        Assert.assertEquals("주문상태는 ORDER 여야함", OrderStatus.ORDER,getOrder.getOrderStatus());
//        Assert.assertEquals("주문 정보의 도시는 대구시 여야함",getOrder.getAddress().getCity(),"대구시");
//    }

}
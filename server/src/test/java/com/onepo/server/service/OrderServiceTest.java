package com.onepo.server.service;

import com.onepo.server.domain.delivery.Address;
import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.delivery.DeliveryStatus;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
import com.onepo.server.domain.wish.Wish;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @Test
    @Rollback(value = false)
    public void 하나만주문() throws Exception {
        Member member=new Member();
        member.register("황효성","hys3396","1234","hys339631@gmail.com");
        memberService.join(member);

        Item item1=new Item();
        item1.createArt("아이폰",50,10000,"보기 좋음");
        itemService.saveItem(item1);

        Item item2=new Item();
        item2.createArt("아이패드",50,20000,"보기 좋음");
        itemService.saveItem(item2);

        Item item3=new Item();
        item3.createArt("맥북",50,30000,"보기 좋음");
        itemService.saveItem(item3);


        Delivery delivery=new Delivery();
        Address address=new Address("대구시","달서구","용산동");
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);


        orderService.order_one(member, delivery, item1, 13);
    }
}
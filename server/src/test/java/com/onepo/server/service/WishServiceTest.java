package com.onepo.server.service;

import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.delivery.DeliveryStatus;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.repository.WishItemRepository;
import com.onepo.server.domain.delivery.Address;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WishServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    WishService wishService;

    @Autowired
    MemberService memberService;

    @Autowired
    WishRepository wishRepository;

    @Autowired
    WishItemRepository wishItemRepository;

    @Test
    @Rollback(value = false)
    public void 장바구니주문() throws Exception {

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

        wishService.addCart(member,item1,2);
        wishService.addCart(member,item2,3);
    }

}
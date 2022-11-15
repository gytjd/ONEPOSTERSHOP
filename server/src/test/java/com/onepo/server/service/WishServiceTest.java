package com.onepo.server.service;

import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.delivery.DeliveryStatus;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.OrderItem;
import com.onepo.server.domain.wish.Wish;
import com.onepo.server.repository.OrderItemRepository;
import com.onepo.server.repository.WishItemRepository;
import com.onepo.server.domain.delivery.Address;


import com.onepo.server.repository.WishRepository;
import org.junit.Assert;
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
    ItemService itemService;
    @Autowired
    MemberService memberService;

    @Autowired
    WishRepository wishRepository;

    @Autowired
    WishItemRepository wishItemRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    @Rollback(value = false)
    public void 장바구니() throws Exception {

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

        Long findCartA = wishService.addCart(member, item1, 2);
        Long findCartB = wishService.addCart(member, item2, 3);

        Wish wishById = wishRepository.findWishById(findCartA);

        Assert.assertEquals("주문 한 사람 이름은 황효성",wishById.getMember().getName(),"황효성");

        Member member2=new Member();
        member2.register("이태곤","taegon1234","1234","taegon@gmail.com");
        memberService.join(member2);

        Delivery delivery2=new Delivery();
        Address address2=new Address("대구시","달서구","용산동");
        delivery2.setAddress(address2);
        delivery2.setStatus(DeliveryStatus.READY);

        Long aLong = wishService.addCart(member2, item2, 3);
        Long aLong1 = wishService.addCart(member2, item3, 5);

        Wish findWishA = wishRepository.findWishById(aLong);

        Assert.assertEquals("주문 한 사람 이름은 이태곤",findWishA.getMember().getName(),"이태곤");
    }


    @Test
    @Rollback(value = false)
    public void 장바구니후주문() throws Exception {

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

        Long findCartA = wishService.addCart(member, item1, 2);
        Long findCartB = wishService.addCart(member, item2, 3);

        Wish wishById = wishRepository.findWishById(findCartA);

        Assert.assertEquals("장바구니 한 사람 이름은 황효성",wishById.getMember().getName(),"황효성");

        Member member2=new Member();
        member2.register("이태곤","taegon1234","1234","taegon@gmail.com");
        memberService.join(member2);

        Delivery delivery2=new Delivery();
        Address address2=new Address("강원도","강릉","바다");
        delivery2.setAddress(address2);
        delivery2.setStatus(DeliveryStatus.READY);

        Long aLong = wishService.addCart(member2, item2, 3);
        Long aLong1 = wishService.addCart(member2, item3, 5);

        Wish findWishA = wishRepository.findWishById(aLong);

        Assert.assertEquals("장바구니 한 사람 이름은 이태곤",findWishA.getMember().getName(),"이태곤");

        Long order = orderService.order(member, delivery);

    }


}
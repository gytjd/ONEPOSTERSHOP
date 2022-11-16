package com.onepo.server.service;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.wish.Wish;
import com.onepo.server.domain.wish.WishItem;
import com.onepo.server.repository.ItemRepository;
import com.onepo.server.repository.WishItemRepository;
import com.onepo.server.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {


    private final ItemRepository itemRepository;

    private final WishItemRepository wishItemRepository;

    private final WishRepository wishRepository;


    public void createCart(Member member) {
        Wish wish=Wish.createCart(member);

        wishRepository.save(wish);

    }
    @Transactional
    public Long addCart(Member member,Item item,int count) {

        Wish wish = wishRepository.findByMemberId(member.getId()); // member 가 장바구니 한거 있나 확인

        if (wish==null) { // 여기서 없으면 그냥 새로 만들어서 save
            wish=Wish.createCart(member);
            wishRepository.save(wish);
        }

        Item findItem = itemRepository.findOne(item.getId()); // 아이템 찾고
        WishItem findWish = wishItemRepository.findByWishIdAndItemId(wish.getId(), findItem.getId());


        if (findWish == null) {
            findWish=WishItem.cartItem(wish,item,count);
            wishItemRepository.save(findWish);
        }
        else {
            WishItem update=findWish;
            update.setWish(findWish.getWish());
            update.setItem(findWish.getItem());
            update.addCount(count);
            update.setWishCount(update.getWishCount());

            wishItemRepository.save(update);
        }

        wish.setCount(wish.getCount()+count);

        return wish.getId();
    }



}
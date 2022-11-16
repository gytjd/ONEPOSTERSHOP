package com.onepo.server.service;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.wish.Wish;
import com.onepo.server.domain.wish.WishItem;
import com.onepo.server.exception.NotExistCartException;
import com.onepo.server.repository.ItemRepository;
import com.onepo.server.repository.WishItemRepository;
import com.onepo.server.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {


    private final ItemService itemService;

    private final WishItemRepository wishItemRepository;

    private final WishRepository wishRepository;


    public void createCart(Member member) {
        Wish wish=Wish.createCart(member);

        save_wish(wish);

    }
    @Transactional
    public Long addCart(Member member,Item item,int count) {

        Wish wish = findByMemberId(member.getId()); // member 가 장바구니 한거 있나 확인

        if (wish==null) { // 여기서 없으면 그냥 새로 만들어서 save
            wish=Wish.createCart(member);
            save_wish(wish);
        }

        Item findItem = itemService.findOne(item.getId()); // 아이템 찾고
        WishItem findWish = findByWishIdAndItemId(wish.getId(), findItem.getId());


        if (findWish == null) {
            findWish=WishItem.cartItem(wish,item,count);
            save_wish_Item(findWish);
        }
        else {
            WishItem update = updateWish(findWish, count);
            save_wish_Item(update);
        }

        wish.addCount(count);

        return wish.getId();
    }

    @Transactional
    public Long subCart(Member member,Item item,int count) {
        Wish wish = findWishByMemberId(member.getId());

        if (wish==null) {
            throw new NotExistCartException();
        } else {
            Item findItem = itemService.findOne(item.getId());
            WishItem findWish = findByWishIdAndItemId(wish.getId(), findItem.getId());

            if(findWish.getWishCount()<=count) {
                if(findWish.getWishCount()<count) {
                    throw new NotExistCartException();
                }
                else {
                    wishItemRepository.delete(findWish);
                }
            }
            else {
                findWish.subCount(count);
            }

            wish.subCount(count);
            findItem.addStock(count);

            if(wish.getCount()==0) {
                wishRepository.delete(wish);
            }

            return wish.getId();
        }

    }


    @Transactional
    public void cancelCart(Member member) {
        Wish findWish = findWishByMemberId(member.getId());
        List<WishItem> items = findWishItemsByWishId(findWish.getId());

        for (WishItem wishItem:items) {

            Item item = wishItem.getItem();
            item.addStock(wishItem.getWishCount());

            wishItemRepository.delete(wishItem);
        }

        wishRepository.delete(findWish);
    }

    public List<WishItem> findWishList(Long id) {
        return findWishItemsByWishId(id);
    }


    public WishItem updateWish(WishItem findWish,int count) {
        WishItem update=findWish;
        update.setWish(findWish.getWish());
        update.setItem(findWish.getItem());
        update.addCount(count);
        update.setWishCount(update.getWishCount());

        return update;
    }

    // WishRepository Service

    public void save_wish(Wish wish) {
        wishRepository.save(wish);
    }

    public Wish findByMemberId(Long id) {
        return wishRepository.findByMemberId(id);
    }

    public Wish findWishById(Long id) {
        return wishRepository.findWishById(id);
    }

    public Wish findWishByMemberId(Long id) {
        return wishRepository.findWishByMemberId(id);
    }





    // WishItemRepository Service

    public void save_wish_Item(WishItem wishItem) {
        wishItemRepository.save(wishItem);
    }

    public WishItem findByWishIdAndItemId(Long wishId,Long itemId) {
        return wishItemRepository.findByWishIdAndItemId(wishId,itemId);
    }

    public List<WishItem> findWishItemsByWishId(Long id) {
        return wishItemRepository.findWishItemsByWishId(id);
    }

    public List<WishItem> findAll() {
        return wishItemRepository.findAll();
    }





}

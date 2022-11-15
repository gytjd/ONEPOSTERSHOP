package com.onepo.server.repository;

import com.onepo.server.domain.wish.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishItemRepository extends JpaRepository<WishItem,Integer> {

    WishItem finByWishIdAndItemId(Long wishId,Long itemId);
    WishItem findWishItemById(Long id);
    List<WishItem> findWishItemByItemId(Long id);
}

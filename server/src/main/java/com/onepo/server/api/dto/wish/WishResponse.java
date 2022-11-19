package com.onepo.server.api.dto.wish;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.wish.WishItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WishResponse {

    // 사진

    private Item item;
    private String itemName;
    private int price;
    private int wishCount;

    public WishResponse(WishItem wishItem) {
        this.item=wishItem.getItem();
        this.itemName = wishItem.getItem().getItemName();
        this.price = wishItem.getItem().getPrice();
        this.wishCount = wishItem.getWishCount();
    }

    public static WishResponse wish_toDto(WishItem wishItem) {
        return new WishResponse(wishItem);
    }
}

package com.onepo.server.api.dto.wish;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.wish.WishItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WishResponse {


    private Long wishItemId;
    private Item item;
    private int wishCount;

    public WishResponse(WishItem wishItem) {
        this.item=wishItem.getItem();
        this.wishItemId=wishItem.getId();
        this.wishCount = wishItem.getWishCount();
    }

    public WishResponse() {
        this.item=null;
        this.wishItemId=null;
        this.wishCount=0;
    }

    public static WishResponse wish_toDto(WishItem wishItem) {
        return new WishResponse(wishItem);
    }

    public static WishResponse noneWish_toDto() {
        return new WishResponse();
    }
}

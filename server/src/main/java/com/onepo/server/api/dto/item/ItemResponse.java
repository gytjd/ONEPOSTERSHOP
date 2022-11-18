package com.onepo.server.api.dto.item;

import com.onepo.server.domain.item.Item;
import lombok.Data;

@Data
public class ItemResponse {

    private String itemName;
    private Integer price;
    private Integer stockQuantity;
    private String description;

    public ItemResponse(Item item) {
        this.itemName=item.getItemName();
        this.price=item.getPrice();
        this.stockQuantity=item.getStockQuantity();
        this.description=item.getDescription();
    }

    public static ItemResponse item_toDto(Item item) {
        return new ItemResponse(item);
    }
}

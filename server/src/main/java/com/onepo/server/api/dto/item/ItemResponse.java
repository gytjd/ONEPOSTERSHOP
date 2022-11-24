package com.onepo.server.api.dto.item;

import com.onepo.server.domain.item.Item;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemResponse {

    private String itemName;
    private Integer price;
    private Integer stockQuantity;

    private String artist;
    private String description;

    private List<String> images;

    public ItemResponse(Item item) {
        this.itemName=item.getItemName();
        this.price=item.getPrice();
        this.stockQuantity=item.getStockQuantity();
        this.artist=item.getArtist();
        this.description=item.getDescription();
        this.images=item.getImages();
    }

    public static ItemResponse item_toDto(Item item) {
        return new ItemResponse(item);
    }
}

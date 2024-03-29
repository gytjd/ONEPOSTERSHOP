package com.onepo.server.domain.item;


import com.onepo.server.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String itemName;
    private int price;
    private int stockQuantity;
    private String artist;
    private String description;
    @ElementCollection
    private List<String> images;

    public Item(String itemName, Integer price, Integer stockQuantity, String description,String artist,List<String> filePath) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.artist=artist;
        this.description = description;
        this.images = filePath;
    }


    public void addStock(int quantity) {
        this.stockQuantity+=quantity;
    }

    public void removeStock(int quantity) {
        int restStock=this.stockQuantity-quantity;

        if (this.stockQuantity==0 || restStock<0) {
            throw new NotEnoughStockException("재고가 더 필요합니다.");
        }

        this.stockQuantity=restStock;
    }

    public void modify(String itemName, Integer price, Integer stockQuantity, String description,String artist,List<String> storeImageFiles) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.artist=artist;
        this.description = description;
        this.images = storeImageFiles;
    }
}

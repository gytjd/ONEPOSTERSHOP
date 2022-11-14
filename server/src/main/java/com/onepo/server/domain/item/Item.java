package com.onepo.server.domain.item;


import com.onepo.server.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String itemName;
    private int stockQuantity;
    private int price;
    private String description;

    public void createArt(String itemName, int stockQuantity, int price, String description) {
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.description = description;
    }

    public void addStock(int quantity) {
        this.stockQuantity+=quantity;
    }

    public void removeStock(int quantity) {
        int restStock=this.stockQuantity-quantity;

        if (restStock<0) {
            throw new NotEnoughStockException("재고가 더 필요합니다.");
        }

        this.stockQuantity=restStock;
    }
}

package com.onepo.server.api.dto.item;


import com.onepo.server.domain.item.Item;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemRegisterRequest {

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String itemName;

    @NotBlank(message="상품 가격을 입력해주세요.")
    private Integer price;

    @NotBlank(message = "상품 수량을 입력해주세요.")
    private Integer stockQuantity;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    private String description;

    public Item toEntity() {
        Item item=new Item();

        item.setItemName(this.itemName);
        item.setPrice(this.price);
        item.setStockQuantity(this.stockQuantity);
        item.setDescription(this.description);

        return item;
    }


}

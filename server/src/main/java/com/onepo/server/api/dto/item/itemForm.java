package com.onepo.server.api.dto.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class itemForm {

    private Long id;

    private String itemName;
    private int price;
    private int stockQuantity;
    private String description;
}

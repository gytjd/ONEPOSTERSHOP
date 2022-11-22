package com.onepo.server.api.dto.order;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.order.OrderItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderListResponse {

    private Integer totalPrice;
    private Integer count;

    private String itemName;

    private Integer itemPrice;
    private List<String> images=new ArrayList<>();

    public OrderListResponse(OrderItem orderItem) {
        this.totalPrice = orderItem.getTotalPrice();
        this.count = orderItem.getCount();
        this.itemName = orderItem.getItem().getItemName();
        this.itemPrice=orderItem.getItem().getPrice();

        for(String image:orderItem.getItem().getImages())
        {
            images.add(image);
        }
    }

    public static OrderListResponse orderList_toDto(OrderItem orderItem) {
        return new OrderListResponse(orderItem);
    }
}

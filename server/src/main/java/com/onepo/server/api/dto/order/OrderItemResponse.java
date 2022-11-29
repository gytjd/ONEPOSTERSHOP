package com.onepo.server.api.dto.order;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.OrderItem;
import lombok.Data;

@Data
public class OrderItemResponse {

    private String Token;
    private Item item;

    public OrderItemResponse(String token,Item item) {
        this.Token=token;
        this.item = item;
    }

    public static OrderItemResponse orderItem_toDto(String token,Item item) {
        return new OrderItemResponse(token,item);
    }
}

package com.onepo.server.api.dto.order;

import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.order.Order;
import com.onepo.server.domain.order.OrderItem;
import com.onepo.server.domain.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {

    private Long orderId;

    private String userName;
    private Long userId;
    private Integer totalPrice;

    private LocalDateTime localDateTime;

    private OrderStatus orderStatus;

    private List<OrderListResponse> orderListResponses=new ArrayList<>();



    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.userName=order.getMember().getName();
        this.userId=order.getMember().getId();
        this.totalPrice=order.getTotalPrice();
        this.localDateTime=order.getOrderDate();
        this.orderStatus=order.getOrderStatus();

        for(OrderItem orderItem:order.getOrderItems()) {
            orderListResponses.add(OrderListResponse.orderList_toDto(orderItem));
        }
    }

    public static OrderResponse order_toDto(Order order) {
        return new OrderResponse(order);
    }
}

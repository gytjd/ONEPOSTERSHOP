package com.onepo.server.repository;

import com.onepo.server.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    List<OrderItem> findOrderItemsByMemberId(Long id);
    List<OrderItem> findAll();

    List<OrderItem> findOrderItemsByOrderId(Long id);
    OrderItem findOrderItemById(Long orderItemId);

}

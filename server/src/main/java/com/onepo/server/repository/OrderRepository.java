package com.onepo.server.repository;

<<<<<<< HEAD
import com.onepo.server.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
=======

import com.onepo.server.domain.order.Order;
import lombok.RequiredArgsConstructor;
>>>>>>> origin/main
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findOrdersByMemberId(Long id);

    Order findOrderById(Long id);


}

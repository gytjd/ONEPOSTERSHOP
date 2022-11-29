package com.onepo.server.service;

import com.onepo.server.api.dto.payment.PaymentDTO;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
import com.onepo.server.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;
    private final MemberService memberService;

    public PaymentDTO initPayment(Long token){
        PaymentDTO paymentDTO = new PaymentDTO();

        Order order = orderService.findOrderById(token);
        Member member = order.getMember();

        List<OrderItem> items = order.getOrderItems();
        String itemName = items.get(0).getItem().getItemName();

        int totalPrice = order.getTotalPrice();
        String name = member.getName();

        paymentDTO.setBuyerName(name);
        paymentDTO.setPrice(totalPrice);
        paymentDTO.setItemName(itemName);
        System.out.println(paymentDTO.getBuyerName());
        System.out.println(paymentDTO.getPrice());
        System.out.println(paymentDTO.getItemName());

        return paymentDTO;
    }
}

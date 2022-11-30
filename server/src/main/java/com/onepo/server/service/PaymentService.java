package com.onepo.server.service;

import com.onepo.server.api.dto.payment.PaymentDTO;
import com.onepo.server.api.dto.wish.WishResponse;
import com.onepo.server.domain.item.Item;
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

    public PaymentDTO initPayment(Long token){
        PaymentDTO paymentDTO = new PaymentDTO();

        Order order = orderService.findOrderById(token);
        Member member = order.getMember();

        String itemName = order.getOrderItems().get(0).getItem().getItemName();

        paymentDTO.setBuyerName(member.getName());
        paymentDTO.setPrice(order.getTotalPrice());
        paymentDTO.setItemName(itemName);


        System.out.println(paymentDTO.getBuyerName());
        System.out.println(paymentDTO.getPrice());
        System.out.println(paymentDTO.getItemName());

        return paymentDTO;
    }


    public PaymentDTO initPayment_byItem(Member member,Item item){
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setBuyerName(member.getName());
        paymentDTO.setPrice(item.getPrice());
        paymentDTO.setItemName(item.getItemName());


        System.out.println(paymentDTO.getBuyerName());
        System.out.println(paymentDTO.getPrice());
        System.out.println(paymentDTO.getItemName());

        return paymentDTO;
    }

    public PaymentDTO initPayment_byWish(Member member, List<WishResponse> wishResponses) {
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setBuyerName(member.getName());

        int totalPrice=0;
        for(WishResponse wishResponse : wishResponses) {
            totalPrice+=wishResponse.getItem().getPrice();
        }

        paymentDTO.setPrice(totalPrice);
        paymentDTO.setItemName(wishResponses.get(0).getItem().getItemName());


        System.out.println(paymentDTO.getBuyerName());
        System.out.println(paymentDTO.getPrice());
        System.out.println(paymentDTO.getItemName());

        return paymentDTO;
    }

}

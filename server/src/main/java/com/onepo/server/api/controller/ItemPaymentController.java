package com.onepo.server.api.controller;


import com.onepo.server.api.dto.payment.PaymentDTO;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
import com.onepo.server.service.MemberService;
import com.onepo.server.service.OrderService;
import com.onepo.server.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class ItemPaymentController {
    private final PaymentService paymentService;
    private Long tokenId;
    private PaymentDTO paymentDTO;
    @ResponseBody
    @PostMapping("member/{orderId}/payment")
    public void getTokenId(@PathVariable("orderId") Long token)
    {

        System.out.println("=================getTokenId 호출==================");
        System.out.println(token);
        tokenId = token;
    }

    @GetMapping("/payment")
    public String paymentForm(Model model){
        System.out.println("================paymentForm 호출====================");

        paymentDTO = paymentService.initPayment(tokenId);

        model.addAttribute("paymentInfo",paymentDTO);
        return "payment/payment";
    }



}


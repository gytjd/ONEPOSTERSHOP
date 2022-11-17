package com.onepo.server.api.controller;

import com.onepo.server.domain.order.OrderItem;
import com.onepo.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final OrderService orderService;

    @RequestMapping
    public String shopHome(Model model) {

        List<OrderItem> all = orderService.findAll();

        return "/shop/shop-view";
    }

    @PostMapping("/register")
    public String shopRegister() {
        return "/shop/shop-register";
    }

}

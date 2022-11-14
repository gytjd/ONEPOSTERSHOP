package com.onepo.server.api.controller;

import com.onepo.server.api.dto.order.OrderForm;
import com.onepo.server.domain.Address;
import com.onepo.server.domain.Delivery;
import com.onepo.server.domain.Member;
import com.onepo.server.domain.item.Item;
import com.onepo.server.service.ItemService;
import com.onepo.server.service.MemberService;
import com.onepo.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;


    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> Members = memberService.findAll();
        List<Item> Items = itemService.findAll();


        model.addAttribute("orderFrom",new OrderForm());
        model.addAttribute("Members",Members);
        model.addAttribute("Items",Items);

        return "orders/createOrderForm";
    }

    @PostMapping("/order")
    public String createOrder(@Valid OrderForm form, BindingResult result,
                              @RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count) {

        if (result.hasErrors()) {
            return "orders/createOrderForm";
        }

        Delivery delivery=new Delivery();
        Address address =new Address(form.getCity(), form.getStreet(),form.getZipcode());
        delivery.setAddress(address);

        orderService.order(memberId,itemId,delivery,count);


        return "redirect:/orders";
    }
}

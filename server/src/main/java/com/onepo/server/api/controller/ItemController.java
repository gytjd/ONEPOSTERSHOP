package com.onepo.server.api.controller;


import com.onepo.server.domain.item.Item;
import com.onepo.server.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @RequestMapping
    public String list(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items",items);


        return "items/itemList";
    }

    @GetMapping("{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,Model model) {
        Item item = itemService.findOne(itemId);

        return "items/updateItemForm";
    }
}

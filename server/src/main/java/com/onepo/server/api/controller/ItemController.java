package com.onepo.server.api.controller;


import com.onepo.server.api.dto.item.ItemForm;
import com.onepo.server.domain.item.Item;
import com.onepo.server.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("form",new ItemForm());

        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(ItemForm form) {

        Item item=new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());
        item.setDescription(form.getDescription());

        itemService.saveItem(item);

        return "redirect:/items";
    }

    @RequestMapping
    public String list(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items",items);


        return "items/itemList";
    }

    @GetMapping("{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,Model model) {
        Item item = itemService.findOne(itemId);

        ItemForm form=new ItemForm();
        form.setId(item.getId());
        form.setItemName(item.getItemName());
        form.setStockQuantity(item.getStockQuantity());
        form.setPrice(item.getPrice());
        form.setDescription(item.getDescription());

        model.addAttribute("form",form);

        return "items/updateItemForm";
    }

    @PostMapping("{itemId}/edit")
    public String updateItem(@ModelAttribute("form") ItemForm form, @PathVariable Long itemId) {
        itemService.updateItem(itemId,form.getItemName(),form.getPrice(),form.getStockQuantity(),form.getDescription());

        return "redirect:/items";
    }
}

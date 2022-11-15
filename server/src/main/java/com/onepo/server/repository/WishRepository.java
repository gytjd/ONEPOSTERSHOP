package com.onepo.server.repository;

import com.onepo.server.domain.OrderItem;
import com.onepo.server.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WishRepository {


    private List<OrderItem> itemList=new ArrayList<>();

    public void cart(OrderItem orderItem) {
        itemList.add(orderItem);
    }

    public List<OrderItem> AllCart() {
        return this.itemList;
    }
 }

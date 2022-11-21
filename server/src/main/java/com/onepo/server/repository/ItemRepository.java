package com.onepo.server.repository;

import com.onepo.server.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findItemById(Long id);
    List<Item> findAll();

}

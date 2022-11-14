package com.onepo.server.service;

import com.onepo.server.domain.item.Item;
import com.onepo.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}

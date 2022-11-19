package com.onepo.server.service;

import com.onepo.server.api.dto.item.ItemModifyRequest;
import com.onepo.server.domain.item.Item;
import com.onepo.server.file.FileStore;
import com.onepo.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

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

    @Transactional
    public void updateItem(Long itemId, ItemModifyRequest request) throws IOException {
        Item findItem = itemRepository.findOne(itemId);
        List<String> storeImageFiles = fileStore.storeFiles(request.getImages());

        findItem.modify(request.getItemName(), request.getPrice(), request.getStockQuantity(),
                request.getDescription(), storeImageFiles);
    }
}

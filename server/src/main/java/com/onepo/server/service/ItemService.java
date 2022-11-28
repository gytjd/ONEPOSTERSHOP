package com.onepo.server.service;

import com.onepo.server.api.dto.item.ItemModifyRequest;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.item.OriginalSeries;
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

    /**
     *
     * @param item
     * 상품 등록
     */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * @return 상품 조회 기능
     */
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findItemById(id);
    }

    public List<Item> findOriginalItems() {
        return itemRepository.findOriginal();
    }

    public List<Item> findCollaborateItems() {
        return itemRepository.findCollaborate();
    }

    /**
     *
     * @param itemId
     * @param request
     * @throws IOException
     * 상품 업데이트
     */
    @Transactional
    public void updateItem(Long itemId, ItemModifyRequest request) throws IOException {
        Item findItem = itemRepository.findItemById(itemId);
        List<String> storeImageFiles = fileStore.storeFiles(request.getImages());

        findItem.modify(
                request.getItemName(),
                request.getPrice(),
                request.getStockQuantity(),
                request.getArtist(),
                request.getDescription(),
                storeImageFiles);
    }

    /**
     *
     * @param item
     * 상품 삭제
     */

    @Transactional
    public void delete_one(Item item) {
        itemRepository.delete(item);
    }
}

package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.item.ItemRegisterRequest;
import com.onepo.server.api.dto.item.ItemResponse;
import com.onepo.server.domain.item.CollaborateSeries;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.item.OriginalSeries;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class ItemApiController {

    private final ItemService itemService;

    private final FileStore fileStore;
    /**
     *
     * 아이템 등록
     */

    @PostMapping("/items/register")
    public ResponseEntity<ResponseDto> saveItem(@Validated ItemRegisterRequest request, BindingResult result) throws IOException {
        List<String> storeFiles = fileStore.storeFiles(request.getImages());

        if(result.hasErrors()) {
            return ResponseEntity.ok().body(new ResponseDto("상품 정보를 다시 입력하세요"));
        }

        if(request.getItemSeries().equals("O")) {

            OriginalSeries originalSeries = request.toEntity_O(storeFiles);
            itemService.saveItem(originalSeries);

        } else if (request.getItemSeries().equals("C")) {

            CollaborateSeries collaborateSeries = request.toEntity_C(storeFiles);
            itemService.saveItem(collaborateSeries);
        } else {
            return ResponseEntity.ok().body(new ResponseDto("상품 카테고리를 다시 입력하세요"));
        }

        return ResponseEntity.ok().body(new ResponseDto("상품 등록이 완료되었습니다."));
    }

    /**
     *
     * 아이템 전체 조회
     */

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponse>> getItems() {
        List<Item> items = itemService.findAll();

        List<ItemResponse> itemResponseList=new ArrayList<>();

        for(Item item:items) {
            itemResponseList.add(ItemResponse.item_toDto(item));
        }

        return ResponseEntity.ok().body(itemResponseList);
    }

    /**
     *
     * @return
     * original Category
     */
    @GetMapping("/items/original")
    public ResponseEntity<List<ItemResponse>> getOriItems() {
        List<Item> items = itemService.findOriginalItems();

        List<ItemResponse> itemResponseList=new ArrayList<>();

        for(Item item:items) {
            itemResponseList.add(ItemResponse.item_toDto(item));
        }

        return ResponseEntity.ok().body(itemResponseList);
    }

    /**
     *
     * @return
     * collaborate Category
     */
    @GetMapping("/items/collaborate")
    public ResponseEntity<List<ItemResponse>> getColItems() {
        List<Item> items = itemService.findCollaborateItems();

        List<ItemResponse> itemResponseList=new ArrayList<>();

        for(Item item:items) {
            itemResponseList.add(ItemResponse.item_toDto(item));
        }

        return ResponseEntity.ok().body(itemResponseList);
    }


    /**
     *
     *
     * 아이템 단건 조회
     */

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable("itemId") Long id) {
        Item one = itemService.findOne(id);

        return ResponseEntity.ok().body(new ItemResponse(one));
    }

    /**
     *
     *
     * 아이템 한개 삭제
     */
    @GetMapping("items/{itemId}/deleteOne")
    public ResponseEntity<ResponseDto> delete_one_item(@PathVariable("itemId") Long id) {
        Item item = itemService.findOne(id);
        itemService.delete_one(item);

        return ResponseEntity.ok().body(new ResponseDto(item.getItemName() + "상품이 삭제 되었습니다."));
    }

    /**
     *
     *
     * 아이템 전체 삭제
     */

    @GetMapping("items/{itemId}/deleteAll")
    public ResponseEntity<ResponseDto> delete_all_item(@PathVariable String itemId) {
        List<Item> items = itemService.findAll();
        for(Item item:items) {
            itemService.delete_one(item);
        }

        return ResponseEntity.ok().body(new ResponseDto("모든 상품이 삭제 되었습니다."));
    }
}


package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.item.ItemRegisterRequest;
import com.onepo.server.api.dto.item.ItemResponse;
import com.onepo.server.domain.item.Item;
import com.onepo.server.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    /**
     *
     * 아이템 등록
     */

    @PostMapping("/items/register")
    public ResponseEntity<ResponseDto> saveItem(@RequestBody ItemRegisterRequest request) throws IOException {

        System.out.println("sex");

        Item item=request.toEntity();
        itemService.saveItem(item);

        return ResponseEntity.ok().body(new ResponseDto("아이템 등록이 완료되었습니다."));
    }

    /**
     *
     * 아이템 조회
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
     *
     * 아이템 단건 조회
     */

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable("itemId") Long id) {
        Item one = itemService.findOne(id);

        return ResponseEntity.ok().body(new ItemResponse(one));
    }




}


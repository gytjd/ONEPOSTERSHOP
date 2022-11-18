package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.item.ItemResponse;
import com.onepo.server.service.MemberService;
import com.onepo.server.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class WishApiController {

    private final WishService wishService;

    private final MemberService memberService;

    @PostMapping("items/{itemId}/wish")
    public ResponseEntity<ResponseDto> wish_item(@PathVariable("itemId") Long id,
                                                 @RequestBody OrderRequest request) {

        return ResponseEntity.ok().body(new ItemResponse());
    }


}

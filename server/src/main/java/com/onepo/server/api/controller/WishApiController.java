package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.item.ItemResponse;
import com.onepo.server.api.dto.wish.WishRequest;
import com.onepo.server.api.dto.wish.WishResponse;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.wish.Wish;
import com.onepo.server.domain.wish.WishItem;
import com.onepo.server.service.ItemService;
import com.onepo.server.service.MemberService;
import com.onepo.server.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class WishApiController {

    private final WishService wishService;

    private final ItemService itemService;

    private final MemberService memberService;

    @PostMapping("items/{itemId}/wish")
    public ResponseEntity<ResponseDto> wish_item(@PathVariable("itemId") Long id,
                                                 @RequestBody WishRequest request) {

        String userId = request.getUserId();
        Member findMember = memberService.findByUserId(userId);

        Long itemId = request.getItemId();
        Item findItem = itemService.findOne(itemId);

        wishService.addCart(findMember,findItem, request.getCount());

        return ResponseEntity.ok().body(new ResponseDto("장바구니에 등록 되었습니다."));
    }

    @GetMapping("/member/{memberId}/wishList")
    public ResponseEntity<List<WishResponse>> all_wish(@PathVariable("memberId") Long id,@RequestBody WishRequest request) {
        String userId = request.getUserId();
        Member byUserId = memberService.findByUserId(userId);

        Wish findWish = wishService.findWishByMemberId(byUserId.getId());
        List<WishItem> wishItemsByWishId = wishService.findWishItemsByWishId(findWish.getId());

        List<WishResponse> wishResponseList=new ArrayList<>();

        return ResponseEntity.ok().body(wishResponseList);

    }





}

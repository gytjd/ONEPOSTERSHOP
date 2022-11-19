package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.item.ItemResponse;
import com.onepo.server.api.dto.wish.WishItemRequest;
import com.onepo.server.api.dto.wish.WishListRequest;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
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

    /**
     *
     * @param id
     * @param request
     * @return
     * 장바구니 등록
     */
    @PostMapping("items/{itemId}/wish")
    public ResponseEntity<ResponseDto> wish_item(@PathVariable("itemId") Long id,
                                                 @RequestBody WishRequest request) {

        String userId = request.getUserId();
        Member findMember = memberService.findByUserId(userId);

        Item findItem = itemService.findOne(id);

        wishService.addCart(findMember,findItem, request.getCount());

        return ResponseEntity.ok().body(new ResponseDto("장바구니에 등록 되었습니다."));
    }

    /**
     *
     * @param id
     * @param wishListRequest
     * @return
     * 장바구니 리스트 확인
     */

    @PostMapping("/member/{memberId}/wishList")
    public ResponseEntity<List<WishResponse>> all_wish(@PathVariable("memberId") Long id,
                                                       @RequestBody WishListRequest wishListRequest) {
        String userId = wishListRequest.getUserId();
        Member byUserId = memberService.findByUserId(userId);

        Wish findWish = wishService.findWishByMemberId(byUserId.getId());

        List<WishResponse> wishResponseList = new ArrayList<>();

        if(findWish==null) {
            wishResponseList.add(WishResponse.noneWish_toDto());
        }
        else {

            List<WishItem> findWishItemList = wishService.findWishItemsByWishId(findWish.getId());

            for (WishItem wishItem : findWishItemList) {
                wishResponseList.add(WishResponse.wish_toDto(wishItem));
            }
        }
        return ResponseEntity.ok().body(wishResponseList);
    }

    /**
     *
     * @param id
     * @param wishItemRequest
     * @return
     * 장바구니 상품 하나 삭제
     */
    @PostMapping("/member/{memberId}/wishList/deleteWishOne")
    public ResponseEntity<ResponseDto> delete_One(@PathVariable("memberId") Long id,
                                                  @RequestBody WishItemRequest wishItemRequest) {

        String userId = wishItemRequest.getUserId();
        Member byUserId = memberService.findByUserId(userId);

        WishItem findwishItem = wishService.findWishItemById(wishItemRequest.getWishItemId());
        Item item = findwishItem.getItem();

        wishService.subCart(byUserId,item,findwishItem.getWishCount());

        return ResponseEntity.ok().body(new ResponseDto(findwishItem.getItem().getItemName()+" 상품을 장바구니에서 삭제하였습니다."));
    }

    /**
     *
     * @param id
     * @return
     * 장바구니 전체 삭제
     */

    @GetMapping("/member/{memberId}/wishList/deleteAllWish")
    public ResponseEntity<ResponseDto> delete_All(@PathVariable("memberId") Long id) {
        Member member = memberService.findOne(id);

        wishService.cancelCart(member);

        return ResponseEntity.ok().body(new ResponseDto("모든 장바구니 물품을 삭제하였습니다."));
    }



}

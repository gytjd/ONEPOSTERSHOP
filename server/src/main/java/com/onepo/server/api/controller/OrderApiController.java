package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.order.OrderOneRequest;
import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.service.ItemService;
import com.onepo.server.service.MemberService;
import com.onepo.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class OrderApiController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @PostMapping("items/{itemId}/order")
    public ResponseEntity<ResponseDto> order_One(@PathVariable("itemId") Long id,
                                                 @Validated @RequestBody OrderOneRequest orderOneRequest,
                                                 BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.ok().body(new ResponseDto("주문 정보를 다시 입력하세요."));
        }

        String userId = orderOneRequest.getUserId();
        Member findMember = memberService.findByUserId(userId);

        Item findItem = itemService.findOne(id);

        Delivery delivery= orderOneRequest.to_Entity();

        orderService.order_One(findMember,delivery,findItem, orderOneRequest.getCount());

        return ResponseEntity.ok().body(new ResponseDto("주문이 완료되었습니다."));
    }
}

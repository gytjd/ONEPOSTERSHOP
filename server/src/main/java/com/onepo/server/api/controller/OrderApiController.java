package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.order.OrderListRequest;
import com.onepo.server.api.dto.order.OrderRequest;
import com.onepo.server.api.dto.order.OrderResponse;
import com.onepo.server.domain.delivery.Delivery;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
import com.onepo.server.service.ItemService;
import com.onepo.server.service.MemberService;
import com.onepo.server.service.OrderService;
import com.onepo.server.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class OrderApiController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    private final WishService wishService;


    @PostMapping("items/{itemId}/order")
    public ResponseEntity<ResponseDto> order_One(@PathVariable("itemId") Long id,
                                                 @Validated @RequestBody OrderRequest orderRequest,
                                                 BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.ok().body(new ResponseDto("주문 정보를 다시 입력하세요."));
        }

        String userId = orderRequest.getUserId();
        Member findMember = memberService.findByUserId(userId);

        Item findItem = itemService.findOne(id);

        Delivery delivery= orderRequest.to_Entity();

        orderService.order_One(findMember,delivery,findItem, orderRequest.getCount());

        return ResponseEntity.ok().body(new ResponseDto("주문이 완료되었습니다."));
    }

    @PostMapping("member/{memberId}/wishList/order")
    public ResponseEntity<ResponseDto> order_wish(@PathVariable("memberId") Long id,
                                                  @Validated @RequestBody OrderRequest orderRequest,
                                                  BindingResult result) {

        if(result.hasErrors()) {
            return ResponseEntity.ok().body(new ResponseDto("주문 정보를 다시 입력하세요"));
        }
        String userId = orderRequest.getUserId();
        Member findMember = memberService.findByUserId(userId);

        Delivery delivery= orderRequest.to_Entity();

        orderService.order_Wish(findMember,delivery);

        return ResponseEntity.ok().body(new ResponseDto("주문이 완료되었습니다."));
    }

    /**
     *
     * @param id
     * @return
     * 주문 내역 전체 확인
     */
    @GetMapping("/member/{memberId}/orderList")
    public ResponseEntity<List<OrderResponse>> find_all_Order(@PathVariable("memberId") Long id)  {

        Member findMember = memberService.findOne(id);

        List<Order> findOrders = orderService.findOrdersByMemberId(findMember.getId());

        List<OrderResponse> orderResponseList=new ArrayList<>();

        for(Order order:findOrders) {
            orderResponseList.add(OrderResponse.order_toDto(order));
        }

        return ResponseEntity.ok().body(orderResponseList);
    }
}

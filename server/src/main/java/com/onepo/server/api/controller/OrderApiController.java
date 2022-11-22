package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.order.OrderListRequest;
import com.onepo.server.api.dto.order.OrderRequest;
import com.onepo.server.api.dto.order.OrderResponse;
import com.onepo.server.api.dto.order.OrderWishRequest;
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


    /**
     *
     * @param id
     * @param orderRequest
     * @param result
     * @return
     * 상품 바로 주문
     */
    @PostMapping("items/{itemId}/order")
    public ResponseEntity<ResponseDto> order_One(@PathVariable("itemId") Long id,
                                                 @Validated @RequestBody OrderRequest orderRequest,
                                                 BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.ok().body(new ResponseDto("주문 정보를 다시 입력하세요."));
        }

        Member member = memberService.findByTokenId(orderRequest.getToken());

        Item findItem = itemService.findOne(id);

        Delivery delivery= orderRequest.to_Entity();

        orderService.order_One(member,delivery,findItem, orderRequest.getCount());

        return ResponseEntity.ok().body(new ResponseDto("주문이 완료되었습니다."));
    }


    /**
     *
     *
     * @param
     * @param result
     * @return
     * 장바구니에 담겨 있는 상품들 주문
     */
    @PostMapping("member/{tokenId}/wishList/order")
    public ResponseEntity<ResponseDto> order_wish(@PathVariable("tokenId") String token,
                                                  @Validated @RequestBody OrderWishRequest orderWishRequest,
                                                  BindingResult result) {

        if(result.hasErrors()) {
            return ResponseEntity.ok().body(new ResponseDto("주문 정보를 다시 입력하세요"));
        }

        Member member = memberService.findByTokenId(token);

        Delivery delivery= orderWishRequest.to_Entity();

        orderService.order_Wish(member,delivery);

        return ResponseEntity.ok().body(new ResponseDto("주문이 완료되었습니다."));
    }

    /**
     *
     *
     * @return
     * 주문 내역 전체 확인
     */
    @GetMapping("/member/{tokenId}/orderList")
    public ResponseEntity<List<OrderResponse>> find_all_Order(@PathVariable("tokenId") String token)  {

        Member member = memberService.findByTokenId(token);

        List<Order> findOrders = orderService.findOrdersByMemberId(member.getId());

        List<OrderResponse> orderResponseList=new ArrayList<>();

        for(Order order:findOrders) {
            orderResponseList.add(OrderResponse.order_toDto(order));
        }

        return ResponseEntity.ok().body(orderResponseList);
    }

    /**
     *
     *
     * @return
     *
     * 모든 주문 삭제
     */

    @PostMapping("/member/{tokenId}/orderList/deleteAll")
    public ResponseEntity<ResponseDto> delete_all_Order(@PathVariable("tokenId") String token) {
        Member member = memberService.findByTokenId(token);
        List<Order> findOrders = orderService.findOrdersByMemberId(member.getId());

        for (Order order:findOrders) {
            orderService.order_Cancel(member,order.getId());
        }

        return ResponseEntity.ok().body(new ResponseDto("모든 주문이 삭제 되었습니다."));
    }

    /**
     *
     *
     * @param request
     * @return
     *
     * 한개 주문 삭제
     */

    @PostMapping("/member/{tokenId}/orderList/deleteOne")
    public ResponseEntity<ResponseDto> delete_one_order(@PathVariable("tokenId") String token,
                                                        @RequestBody OrderListRequest request) {
        Member member = memberService.findByTokenId(token);
        orderService.order_Cancel(member, request.getOrderId());

        return ResponseEntity.ok().body(new ResponseDto("주문이 삭제 되었습니다."));
    }
}

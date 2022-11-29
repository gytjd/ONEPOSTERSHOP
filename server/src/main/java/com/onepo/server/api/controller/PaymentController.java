package com.onepo.server.api.controller;


import com.onepo.server.api.dto.order.OrderItemRequest;
import com.onepo.server.api.dto.payment.PaymentDTO;
import com.onepo.server.api.dto.payment.PaymentInfo;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
import com.onepo.server.service.ItemService;
import com.onepo.server.service.MemberService;
import com.onepo.server.service.OrderService;
import com.onepo.server.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.onepo.server.api.dto.payment.payStatus.paid;

@Log4j2
@Controller
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {

    private final IamportClient api = new IamportClient("5433251341413542", "SJK1bopNos7RcY1VPTloUlW5DYC0l9jgP62pGg7yKLNMIzgv8PnPq1AvAceqLzGQgTti9nTLNC72PgTR");

    private final PaymentService paymentService;

    private final MemberService memberService;

    private final ItemService itemService;
    private Long tokenId;
    private PaymentDTO paymentDTO;



    @GetMapping("/payment")
    public String paymentForm(Model model){
        System.out.println("================paymentForm 호출====================");

        paymentDTO = paymentService.initPayment(tokenId);

        model.addAttribute("paymentInfo",paymentDTO);
        return "payment/payment";
    }


    /**
     *
     * @param model
     * @param itemId
     * @param request
     * @return 결제
     */

    @PostMapping("/api/items/{itemId}/payment")
    public String payment_OneForm(Model model,
                               @PathVariable Long itemId,
                               @RequestBody OrderItemRequest request) {

        String token = request.getToken();
        Member findMember = memberService.findByTokenId(token);

        Item findItem = itemService.findOne(itemId);
        paymentDTO = paymentService.initPayment_byItem(findMember, findItem);

        model.addAttribute("paymentInfo",paymentDTO);

        return "payment/paymentOne";
    }


    @ResponseBody
    @PostMapping("member/{orderId}/payment")
    public void getTokenId(@PathVariable("orderId") Long token)
    {

        System.out.println("=================getTokenId 호출==================");
        System.out.println(token);
        tokenId=token;
    }

    @PostMapping("/payment")
    public @ResponseBody String payment(@RequestBody PaymentInfo paymentInfo){
        System.out.println("출력");
        System.out.println(paymentInfo.getName());
        System.out.println(paymentInfo.getAmount());
        System.out.println(paymentInfo.getMerUid());
        // paymentRepository.save(paymentDTO);
        // paymentService.savePayment(paymentDTO); //수정해야함

        return "OK";
    }


    @RequestMapping(value="/verifyIamport/{imp_uid}")
    public @ResponseBody IamportResponse<Payment> paymentByImpUid(@PathVariable(value= "imp_uid") String imp_uid,
                                                                  @RequestBody PaymentInfo paymentInfo) throws IamportResponseException, IOException
    {
        paymentDTO = paymentService.initPayment(tokenId);
        System.out.println("======Test========");
        System.out.println(paymentDTO.getBuyerName());
        System.out.println(paymentDTO.getPrice());
        System.out.println(paymentDTO.getItemName());
        System.out.println("======Test========");
        System.out.println(paymentInfo.getAmount());


        if(paymentInfo.getStatus() == paid && paymentInfo.getAmount().intValue() == paymentDTO.getPrice()) {
            System.out.println("검증성공");
            return api.paymentByImpUid(imp_uid);
        }
        else {
            System.out.println("검증실패");
        }
        return null;
    }



}
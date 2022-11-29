package com.onepo.server.api.controller;


import com.onepo.server.api.dto.payment.PaymentDTO;
import com.onepo.server.api.dto.payment.PaymentInfo;
import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.order.Order;
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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.onepo.server.api.dto.payment.payStatus.paid;

@Log4j2
@Controller
@CrossOrigin
public class PaymentController {

    private final IamportClient api;

    private final PaymentService paymentService;

    private Long tokenId;
    private PaymentDTO paymentDTO;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.api = new IamportClient("5433251341413542", "SJK1bopNos7RcY1VPTloUlW5DYC0l9jgP62pGg7yKLNMIzgv8PnPq1AvAceqLzGQgTti9nTLNC72PgTR");
        this.paymentService = paymentService;
    }

    @ResponseBody
    @PostMapping("member/{orderId}/payment/complete")
    public void getTokenId(@PathVariable("orderId") Long token)
    {
        System.out.println("=================PaymentController getTokenId 호출==================");
        tokenId = token;
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



}
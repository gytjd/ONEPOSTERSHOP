package com.onepo.server.api.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentInfo {
    private String payNum;
    private String name;

    private payStatus status;
    @JsonProperty("paid_amount")
    private BigDecimal amount;

    @JsonProperty("buyerName")
    private String userId;

    @JsonProperty("merchant_uid")
    private String merUid;

    private Integer payAmount;
    private String payEmail;
    private String payMethod;
    private String payAgreeYN;
    private String payCard;
}

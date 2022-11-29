package com.onepo.server.api.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentInfo {
    private String payNum;
    private String name;

    private payStatus status;
    private BigDecimal amount;

    private String userId;

    private String merUid;

    private Integer payAmount;
    private String payEmail;
    private String payMethod;
    private String payAgreeYN;
    private String payCard;
}

package com.onepo.server.api.dto.payment;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class PaymentDTO {
    private String buyerName;
    private Integer price;
    private String token;
    private String itemName;
}

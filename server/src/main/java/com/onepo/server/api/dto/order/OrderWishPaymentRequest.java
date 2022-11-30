package com.onepo.server.api.dto.order;

import com.onepo.server.api.dto.wish.WishResponse;
import lombok.Data;

import java.util.List;

@Data
public class OrderWishPaymentRequest {

    private List<WishResponse> wishResponseList;

}

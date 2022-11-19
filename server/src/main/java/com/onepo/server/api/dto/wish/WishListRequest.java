package com.onepo.server.api.dto.wish;

import lombok.Data;

@Data
public class WishListRequest {

    private Long memberId;
    private String userId;
}

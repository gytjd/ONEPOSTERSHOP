package com.onepo.server.api.dto.wish;

import lombok.Data;

@Data
public class WishItemRequest {

    private Long memberId;
    private String userId;
    private Long wishItemId;


}

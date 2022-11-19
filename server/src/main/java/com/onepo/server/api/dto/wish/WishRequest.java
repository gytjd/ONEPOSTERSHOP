package com.onepo.server.api.dto.wish;

import lombok.Data;

@Data
public class WishRequest {

    private Long memberId;

    private String userId;

    private String userName;

    private Long itemId;

    private int count;
}

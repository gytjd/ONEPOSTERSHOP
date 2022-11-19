package com.onepo.server.api.dto.wish;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WishRequest {

    private Long memberId;

    private String userId;

    private int count;
}

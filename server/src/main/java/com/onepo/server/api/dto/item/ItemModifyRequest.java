package com.onepo.server.api.dto.item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ItemModifyRequest {
    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String itemName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stockQuantity;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    private String description;

    private List<MultipartFile> images;
}

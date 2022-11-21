package com.onepo.server.api.dto.item;


import com.onepo.server.domain.item.Item;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ItemRegisterRequest {

    @NotBlank(message = "상품 이름을 입력해주세요.")
    private String itemName;

    @NotNull(message = "상품 가격을 입력해주세요.")
    private Integer price;

    @NotNull(message = "상품 수량을 입력해주세요.")
    private Integer stockQuantity;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    private String description;

    private List<MultipartFile> images;

    public Item toEntity(List<String> filePath) {
        Item item=new Item(itemName, price, stockQuantity, description, filePath);

        return item;
    }


}

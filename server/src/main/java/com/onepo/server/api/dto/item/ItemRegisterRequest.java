package com.onepo.server.api.dto.item;


import com.onepo.server.domain.item.CollaborateSeries;
import com.onepo.server.domain.item.Item;
import com.onepo.server.domain.item.OriginalSeries;
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

    @NotBlank(message="상품 작가를 입력해주세요")
    private String artist;

    @NotBlank(message = "상품 설명을 입력해주세요.")
    private String description;

    private List<MultipartFile> images;

    @NotBlank(message = "상품 카테고리를 입력하세요.")
    private String itemSeries;


    public OriginalSeries toEntity_O(List<String> filePath) {
        OriginalSeries originalSeries=new OriginalSeries(itemName, price, stockQuantity,artist,description, filePath);

        return originalSeries;
    }

    public CollaborateSeries toEntity_C(List<String> filePath) {
        CollaborateSeries collaborateSeries=new CollaborateSeries(itemName,price,stockQuantity,artist,description,filePath);

        return collaborateSeries;
    }






}

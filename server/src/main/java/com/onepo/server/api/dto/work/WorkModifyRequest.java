package com.onepo.server.api.dto.work;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class WorkModifyRequest {

    @NotBlank(message = "작가를 입력해주세요.")
    private String author;
    @NotBlank(message = "작품 이름을 입력해주세요.")
    private String title;
    private String description;
    private List<MultipartFile> imageFiles;
}

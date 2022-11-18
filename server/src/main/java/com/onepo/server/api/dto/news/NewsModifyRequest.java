package com.onepo.server.api.dto.news;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class NewsModifyRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String content;
    private MultipartFile imageFile;

}

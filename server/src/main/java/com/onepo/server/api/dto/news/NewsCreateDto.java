package com.onepo.server.api.dto.news;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NewsCreateDto {
    private String title;
    private String content;
    private MultipartFile imageFile;
}

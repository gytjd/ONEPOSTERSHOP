package com.onepo.server.api.dto.news;

import com.onepo.server.domain.News;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NewsCreateDto {
    private String title;
    private String content;
    private MultipartFile imageFile;

    public News toEntity(String filePath) {
        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setImageFiles(filePath);

        return news;
    }
}

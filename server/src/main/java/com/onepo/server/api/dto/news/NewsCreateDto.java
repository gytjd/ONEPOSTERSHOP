package com.onepo.server.api.dto.news;

import com.onepo.server.domain.News;
import jdk.jshell.execution.LoaderDelegate;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
        news.setLocalDateTime(LocalDateTime.now());
        news.setImageFiles(filePath);

        return news;
    }
}

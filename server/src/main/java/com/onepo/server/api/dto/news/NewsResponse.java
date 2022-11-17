package com.onepo.server.api.dto.news;

import com.onepo.server.domain.news.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class NewsResponse {

    private String title;
    private String content;
    private LocalDateTime localDateTime;
    private String imageFiles;

    public NewsResponse(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.localDateTime = news.getLocalDateTime();
        this.imageFiles = news.getImageFiles();
    }

    public static NewsResponse toDTO(News news) {
        return new NewsResponse(news);
    }
}

package com.onepo.server.api.dto.news;

import com.onepo.server.domain.news.News;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
public class NewsResponse {

    private String title;
    private String content;
    private String localDateTime;
    private String imageFiles;

    public NewsResponse(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.localDateTime = news.getLocalDateTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
        this.imageFiles = news.getImageFile();
    }

    public static NewsResponse toDTO(News news) {
        return new NewsResponse(news);
    }
}

package com.onepo.server.api.dto.news;

import com.onepo.server.domain.news.News;
import lombok.Data;


@Data
public class NewsCreateResponse {

    private String title;
    private String content;
    private String path;

    public NewsCreateResponse(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.path = news.getImageFiles();
    }
}

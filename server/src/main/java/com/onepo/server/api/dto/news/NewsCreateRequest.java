package com.onepo.server.api.dto.news;

import com.onepo.server.domain.news.News;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class NewsCreateRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String content;

    private MultipartFile imageFile;

    public News toEntity(String filePath) {
        News news = new News();

        news.setTitle(title);
        news.setContent(content);
        news.setLocalDateTime(LocalDateTime.now());
        news.setImageFile(filePath);

        return news;
    }
}

package com.onepo.server.domain.news;

import com.onepo.server.api.dto.news.NewsModifyRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private LocalDateTime localDateTime;
    private String imageFile;

    public void modify(String title, String content, String storeFileName) {
        this.title = title;
        this.content = content;
        this.imageFile = storeFileName;
    }
}

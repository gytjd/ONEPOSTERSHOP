package com.onepo.server.domain.news;

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

    @NotEmpty
    private String title;

    @NotNull
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime localDateTime;

    @NotEmpty
    private String imageFiles;
}

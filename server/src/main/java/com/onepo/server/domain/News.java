package com.onepo.server.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue
    @Column(name = "NEWS_ID")
    private Long id;

    private String title;

    private String content;

    private LocalDateTime localDateTime;

    private String imageFiles;
}

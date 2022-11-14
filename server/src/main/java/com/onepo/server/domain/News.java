package com.onepo.server.domain;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class News {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private LocalDateTime localDateTime;

    private String content;

}

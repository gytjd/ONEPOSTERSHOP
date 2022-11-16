package com.onepo.server.api.dto.work;

import com.onepo.server.domain.news.News;
import com.onepo.server.domain.work.Work;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkCreateDto {

    private String author;

    private String name;

    private String description;
    
    private List<MultipartFile> imageFiles;
}

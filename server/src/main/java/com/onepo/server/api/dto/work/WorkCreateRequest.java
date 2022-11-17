package com.onepo.server.api.dto.work;

import com.onepo.server.domain.news.News;
import com.onepo.server.domain.work.Work;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkCreateRequest {

    @NotBlank(message = "작가를 입력해주세요.")
    private String author;

    @NotBlank(message = "작품 이름을 입력해주세요.")
    private String title;

    private String description;

    private List<MultipartFile> imageFiles;

    public Work toEntity(List<String> filePath) {
        Work work = new Work();

        work.setAuthor(author);
        work.setTitle(title);
        work.setDescription(description);
        work.setImageFiles(filePath);

        return work;
    }
}

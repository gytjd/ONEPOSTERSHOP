package com.onepo.server.api.dto.work;

import com.onepo.server.domain.work.Work;
import lombok.Data;

import java.util.List;

@Data
public class WorkResponse {

    private String author;
    private String title;
    private String description;
    private List<String> images;

    public WorkResponse(Work work) {
        this.author = work.getAuthor();
        this.title = work.getTitle();
        this.description = work.getDescription();
        this.images = work.getImageFiles();
    }

    public static WorkResponse toDTO(Work work) {
        return new WorkResponse(work);
    }
}

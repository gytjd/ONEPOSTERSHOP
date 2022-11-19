package com.onepo.server.domain.work;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Work {

    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String title;
    private String description;
    @ElementCollection
    private List<String> imageFiles;

    public Work(String author, String title, String description, List<String> filePath) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.imageFiles = filePath;
    }

    public void modify(String author, String title, String description, List<String> imageFiles) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.imageFiles = imageFiles;
    }
}

package com.onepo.server.domain.work;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Work {

    @Id
    @GeneratedValue
    private Long id;

    private String author;

    private String name;

    private String description;

    @ElementCollection
    private List<String> imageFiles;

}

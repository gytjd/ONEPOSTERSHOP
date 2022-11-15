package com.onepo.server.domain.work;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Work {

    @Id
    @GeneratedValue
    private Long id;

    private String author;

    private String name;

    private String description;

    private List<String> imageFiles = new ArrayList<String>();
}

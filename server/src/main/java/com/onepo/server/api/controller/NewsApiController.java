package com.onepo.server.api.controller;

import com.onepo.server.api.dto.news.NewsCreateDto;
import com.onepo.server.domain.news.News;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class NewsApiController {

    private final NewsService newsService;

    private final FileStore fileStore;

    @GetMapping("/news/new")
    public NewsCreateDto createNews(@ModelAttribute NewsCreateDto dto) {
        return new NewsCreateDto();
    }

    @PostMapping("/news/new")
    public void create(@ModelAttribute @RequestBody NewsCreateDto dto) throws IOException {
        String storeImageFiles = fileStore.storeFile(dto.getImageFile());

        News news = dto.toEntity(storeImageFiles);
        newsService.create(news);
    }

    @GetMapping("/news/{id}")
    public String News(@PathVariable Long id) {
        News news = newsService.findOne(id);

        return "news-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws
            MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}

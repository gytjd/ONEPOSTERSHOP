package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.news.NewsCreateRequest;
import com.onepo.server.api.dto.news.NewsResponse;
import com.onepo.server.domain.news.News;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api")
public class NewsApiController {

    private final NewsService newsService;

    private final FileStore fileStore;

    /**
     * 새로운 뉴스 등록
     */
    @PostMapping("/news/create")
    public ResponseEntity<ResponseDto> create(@ModelAttribute @RequestBody NewsCreateRequest request) throws IOException {
        String storeImageFiles = fileStore.storeFile(request.getImageFile());

        News news = request.toEntity(storeImageFiles);
        newsService.create(news);

        return ResponseEntity.ok().body(new ResponseDto("게시물이 정상적으로 등록되었습니다."));
    }
    /**
     * 뉴스 조회
     */
    @GetMapping("/news")
    public ResponseEntity<List<NewsResponse>> News() {
        List<News> newsList = newsService.findAll();

        List<NewsResponse> newsResponseList = newsList.stream().map(NewsResponse::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(newsResponseList);
    }
}

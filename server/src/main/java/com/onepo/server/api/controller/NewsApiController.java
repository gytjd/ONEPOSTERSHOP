package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.news.NewsCreateRequest;
import com.onepo.server.api.dto.news.NewsModifyRequest;
import com.onepo.server.api.dto.news.NewsResponse;
import com.onepo.server.domain.news.News;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
     * 뉴스 등록
     */
    @PostMapping("/news/save")
    public ResponseEntity<ResponseDto> saveNews(@Validated NewsCreateRequest request) throws IOException {
        String storeImageFiles = fileStore.storeFile(request.getImageFile());

        News news = request.toEntity(storeImageFiles);
        newsService.create(news);

        return ResponseEntity.ok().body(new ResponseDto("게시물이 정상적으로 등록되었습니다."));
    }
    /**
     * 뉴스 조회
     */
    @GetMapping("/news")
    public ResponseEntity<List<NewsResponse>> getNewsList() {
        List<News> newsList = newsService.findAll();

        List<NewsResponse> newsResponseList = newsList.stream().map(NewsResponse::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(newsResponseList);
    }

    /**
     * 뉴스 단건 조회
     * @param id
     */
    @GetMapping("/news/{id}")
    public ResponseEntity<NewsResponse> getNews(@PathVariable("id") Long id) {
        News news = newsService.findOne(id);

        return ResponseEntity.ok().body(new NewsResponse(news));
    }

    /**
     * 뉴스 정보 변경
     * @param id
     */
    @PutMapping("news/{id}")
    public ResponseEntity<ResponseDto> modifyNews(@PathVariable("id") Long id,
                                                  @Validated NewsModifyRequest request,
                                                  BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDto("게시물 정보를 한번 더 확인해주세요."));
        }
        newsService.updateNews(id, request);
        return ResponseEntity.ok().body(new ResponseDto("수정이 완료되었습니다."));
    }
}

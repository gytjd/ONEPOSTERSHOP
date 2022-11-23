package com.onepo.server.service;

import com.onepo.server.api.dto.news.NewsModifyRequest;
import com.onepo.server.domain.news.News;
import com.onepo.server.file.FileStore;
import com.onepo.server.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {

    private final NewsRepository newsRepository;
    private final FileStore fileStore;

    /**
     *
     * @param news
     * @return
     * 뉴스 생성
     */
    public Long create(News news) {
        newsRepository.save(news);
        return news.getId();
    }

    /**
     *
     * @param id
     * @return
     * 뉴스 조회
     */

    public News findOne(Long id) {
        return newsRepository.findById(id);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    /**
     *
     * @param id
     * @param request
     * @throws IOException
     * 뉴스 업데이트
     */

    public void updateNews(Long id, NewsModifyRequest request) throws IOException {
        News news = newsRepository.findById(id);
        String storeImageFile = fileStore.storeFile(request.getImageFile());

        news.modify(request.getTitle(), request.getContent(), storeImageFile);
    }

    /**
     *
     * @param id
     * 뉴스 삭제
     */
    public void delete(Long id) {
        News news = newsRepository.findById(id);
        newsRepository.remove(news);
    }
}

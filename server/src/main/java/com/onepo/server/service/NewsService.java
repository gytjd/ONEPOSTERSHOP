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
    public Long create(News news) {
        newsRepository.save(news);
        return news.getId();
    }

    public News findOne(Long id) {
        return newsRepository.findById(id);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public void updateNews(Long id, NewsModifyRequest request) throws IOException {
        News news = newsRepository.findById(id);
        String storeImageFile = fileStore.storeFile(request.getImageFile());

        news.modify(request.getTitle(), request.getContent(), storeImageFile);
    }

    public void delete(Long id) {
        News news = newsRepository.findById(id);
        newsRepository.remove(news);
    }
}

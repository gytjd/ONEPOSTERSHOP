package com.onepo.server.service;

import com.onepo.server.domain.News;
import com.onepo.server.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {

    private final NewsRepository newsRepository;

    public Long create(News news) {
        newsRepository.save(news);
        return news.getId();
    }

    public News findOne(Long id) {
        return newsRepository.findById(id);
    }
}

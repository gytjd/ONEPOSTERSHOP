package com.onepo.server.repository;

import com.onepo.server.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NewsRepository {

    private final EntityManager em;

    public News save(News news) {
        em.persist(news);
        return news;
    }

    public News findById(Long id) {
        return em.find(News.class, id);
    }
}

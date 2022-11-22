package com.onepo.server.repository;

import com.onepo.server.domain.member.Member;
import com.onepo.server.domain.news.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<News> findAll() {
        return em.createQuery("select n from News As n", News.class)
                .getResultList();
    }

    public void remove(News news) {
        em.remove(news);
    }
}

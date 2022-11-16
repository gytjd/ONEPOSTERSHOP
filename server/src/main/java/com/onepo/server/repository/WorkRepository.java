package com.onepo.server.repository;

import com.onepo.server.domain.news.News;
import com.onepo.server.domain.work.Work;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WorkRepository {

    private final EntityManager em;

    public Work save(Work work) {
        em.persist(work);
        return work;
    }

    public Work findById(Long id) {
        return em.find(Work.class, id);
    }
}

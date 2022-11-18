package com.onepo.server.service;

import com.onepo.server.domain.news.News;
import com.onepo.server.domain.work.Work;
import com.onepo.server.repository.NewsRepository;
import com.onepo.server.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkService {

    private final WorkRepository workRepository;

    public Long create(Work work) {
        workRepository.save(work);
        return work.getId();
    }

    public Work findOne(Long id) {
        return workRepository.findById(id);
    }

    public List<Work> findAll() {
        return workRepository.findAll();
    }
}

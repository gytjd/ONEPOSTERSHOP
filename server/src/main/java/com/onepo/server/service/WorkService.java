package com.onepo.server.service;

import com.onepo.server.api.dto.work.WorkModifyRequest;
import com.onepo.server.domain.news.News;
import com.onepo.server.domain.work.Work;
import com.onepo.server.file.FileStore;
import com.onepo.server.repository.NewsRepository;
import com.onepo.server.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkService {

    private final WorkRepository workRepository;
    private final FileStore fileStore;

    /**
     *
     * @param work
     * @return
     * 작품 생성
     */
    public Long create(Work work) {
        workRepository.save(work);
        return work.getId();
    }


    /**
     *
     * @param id
     * @return
     * 작품 조회
     */
    public Work findOne(Long id) {
        return workRepository.findById(id);
    }

    public List<Work> findAll() {
        return workRepository.findAll();
    }


    /**
     *
     * @param id
     * @param request
     * @throws IOException
     * 작품 업데이트
     */
    public void updateWork(Long id, WorkModifyRequest request) throws IOException {
        Work work = workRepository.findById(id);
        List<String> storeImageFiles = fileStore.storeFiles(request.getImageFiles());

        work.modify(request.getAuthor(), request.getTitle(), request.getDescription(), storeImageFiles);
    }

    /**
     *
     * @param id
     * 작품 업데이트
     */
    public void delete(Long id) {
        Work work = workRepository.findById(id);
        workRepository.remove(work);
    }
}

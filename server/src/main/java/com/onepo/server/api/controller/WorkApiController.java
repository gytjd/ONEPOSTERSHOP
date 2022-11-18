package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.work.WorkCreateRequest;
import com.onepo.server.api.dto.work.WorkResponse;
import com.onepo.server.domain.work.Work;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class WorkApiController {

    private final WorkService workService;

    private final FileStore fileStore;

    /**
     * 작품 등록
     */
    @PostMapping("/work/save")
    public ResponseEntity<ResponseDto> saveWork(@ModelAttribute WorkCreateRequest request) throws IOException {
        List<String> storeFiles = fileStore.storeFiles(request.getImageFiles());

        Work work = request.toEntity(storeFiles);
        workService.create(work);

        return ResponseEntity.ok().body(new ResponseDto("작품이 등록되었습니다."));
    }

    /**
     * 작품 조회
     */
    @GetMapping("/work")
    public ResponseEntity<List<WorkResponse>> getWorkList() {
        List<Work> works = workService.findAll();

        List<WorkResponse> workResponseList = works.stream().map(WorkResponse::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(workResponseList);
    }

    /**
     * 작품 단건 조회
     * @param id
     */
    @GetMapping("/work/{id}")
    public ResponseEntity<WorkResponse> getWork(@PathVariable("id") Long id) {
        Work work = workService.findOne(id);

        return ResponseEntity.ok().body(new WorkResponse(work));
    }

}

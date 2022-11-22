package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.work.WorkCreateRequest;
import com.onepo.server.api.dto.work.WorkModifyRequest;
import com.onepo.server.api.dto.work.WorkResponse;
import com.onepo.server.domain.work.Work;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.WorkService;
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
@RequestMapping("/api/work")
@CrossOrigin
public class WorkApiController {

    private final WorkService workService;

    private final FileStore fileStore;

    /**
     * 작품 등록
     */
    @PostMapping("/save")
    public ResponseEntity<ResponseDto> saveWork(@Validated WorkCreateRequest request) throws IOException {
        List<String> storeFiles = fileStore.storeFiles(request.getImageFiles());

        Work work = request.toEntity(storeFiles);
        workService.create(work);

        return ResponseEntity.ok().body(new ResponseDto("작품이 등록되었습니다."));
    }

    /**
     * 작품 조회
     */
    @GetMapping
    public ResponseEntity<List<WorkResponse>> getWorkList() {
        List<Work> works = workService.findAll();

        List<WorkResponse> workResponseList = works.stream().map(WorkResponse::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(workResponseList);
    }

    /**
     * 작품 단건 조회
     * @param id
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkResponse> getWork(@PathVariable("id") Long id) {
        Work work = workService.findOne(id);

        return ResponseEntity.ok().body(new WorkResponse(work));
    }

    /**
     * 작품 정보 변경
     * @param id
     */
    @PutMapping("/modify/{id}")
    public ResponseEntity<ResponseDto> modifyWork(@PathVariable("id") Long id,
                                                  @Validated WorkModifyRequest request,
                                                  BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDto("작품 정보를 한번 더 확인해주세요."));
        }

        workService.updateWork(id, request);
        return ResponseEntity.ok().body(new ResponseDto("작품 수정이 완료되었습니다."));
    }

    /**
     * 작품 삭제
     * @param id
     */
    @PostMapping("/delete/{id}")
    private ResponseEntity<ResponseDto> deleteWork(@PathVariable("id") Long id) {
        workService.delete(id);
        return ResponseEntity.ok().body(new ResponseDto("작품이 삭제되었습니다."));
    }

}

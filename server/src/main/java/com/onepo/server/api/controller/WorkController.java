package com.onepo.server.api.controller;

import com.onepo.server.api.dto.ResponseDto;
import com.onepo.server.api.dto.work.WorkCreateRequest;
import com.onepo.server.domain.work.Work;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class WorkController {

    private final WorkService workService;

    private final FileStore fileStore;

    @PostMapping("/work/create")
    public ResponseEntity<ResponseDto> saveItem(@ModelAttribute WorkCreateRequest request) throws IOException {
        List<String> storeFiles = fileStore.storeFiles(request.getImageFiles());

        Work work = request.toEntity(storeFiles);
        workService.create(work);

        return ResponseEntity.ok().body(new ResponseDto("작품이 등록되었습니다."));
    }

}

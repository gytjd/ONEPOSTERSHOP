package com.onepo.server.api.controller;

import com.onepo.server.api.dto.work.WorkCreateDto;
import com.onepo.server.domain.work.Work;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class WorkController {

    private final WorkService workService;

    private final FileStore fileStore;

    @GetMapping("/work/new")
    public String newItem(@ModelAttribute WorkCreateDto dto) {
        return "work-form";
    }

    @PostMapping("/work/new")
    public String saveItem(@ModelAttribute WorkCreateDto dto, RedirectAttributes redirectAttributes) throws IOException {
        List<String> storeFiles = fileStore.storeFiles(dto.getImageFiles());

        Work work = new Work();

        work.setAuthor(dto.getAuthor());
        work.setName(dto.getName());
        work.setDescription(dto.getDescription());
        work.setImageFiles(storeFiles);

        workService.create(work);

        redirectAttributes.addAttribute("workId", work.getId());

        return "redirect:/work/{workId}";
    }

    @GetMapping("/work/{id}")
    public String items(@PathVariable Long id, Model model) {
        Work work = workService.findOne(id);
        model.addAttribute("work", work);
        return "work-view";
    }

    @ResponseBody
    @GetMapping("/workImages/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws
            MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}

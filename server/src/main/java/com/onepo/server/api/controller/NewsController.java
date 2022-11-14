package com.onepo.server.api.controller;

import com.onepo.server.api.dto.news.NewsCreateDto;
import com.onepo.server.domain.News;
import com.onepo.server.file.FileStore;
import com.onepo.server.service.NewsService;
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
public class NewsController {

    private final NewsService newsService;

    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute NewsCreateDto dto) {
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute NewsCreateDto dto, RedirectAttributes redirectAttributes) throws IOException {
        String storeImageFiles = fileStore.storeFile(dto.getImageFile());

        News news = new News();

        newsService.create(news);

        redirectAttributes.addAttribute("itemId", news.getId());

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        News news = newsService.findOne(id);
        model.addAttribute("news", news);
        return "item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws
            MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}

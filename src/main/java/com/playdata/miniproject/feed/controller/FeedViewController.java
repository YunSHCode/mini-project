package com.playdata.miniproject.feed.controller;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.service.FeedService;
import com.playdata.miniproject.feed.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedViewController {


    @GetMapping("/")
    public String feed() {
        return "/feed/feed";
    }

    @GetMapping("/upload")
    public String upload() {
        return "/feed/upload_feed";
    }


    @GetMapping("/myfeed")
    public String myfeed() {
        return "/feed/myfeed";
    }

    @GetMapping("/editFeed")
    public String editFeed() {
        return "/feed/edit_feed";
    }
}

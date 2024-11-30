package com.playdata.miniproject.feed.controller;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.service.FeedService;
import com.playdata.miniproject.feed.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
@Slf4j
public class FeedViewController {

    private final FeedService feedService;


    @Value("${file.dir}")
    private String UPLOADPATH; // "C:/feedImg/" 파일을 저장할 경로

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


    // 피드 ID 생성 (예시로 랜덤 값을 사용)
    private int generateFeedId() {
        // 실제로는 DB에서 피드 ID를 자동 생성하는 방식 사용
        return (int) (Math.random() * 10000); // 랜덤 ID 예시
    }


}

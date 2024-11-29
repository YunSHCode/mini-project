package com.playdata.miniproject.feed.controller;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.service.FeedService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FeedDataController {

    private final FeedService feedService;



    // 피드 업로드 및 파일 저장
    @PostMapping("/feed/upload")
    public String responseUpload(
            HttpServletResponse response,
            @RequestPart(value = "feedFile", required = false) MultipartFile[] uploadFiles, // 여러 개의 파일을 처리
            @RequestParam("content") String content,
            @RequestParam("diet") String[] diet) throws IOException {

        feedService.feedUpload(uploadFiles,content, diet);

        response.sendRedirect("/feed/upload");
        return "알 수 없는 오류가 발생했습니다.";
    }


}

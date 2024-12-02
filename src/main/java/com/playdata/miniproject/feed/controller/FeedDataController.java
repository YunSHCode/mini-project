package com.playdata.miniproject.feed.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class FeedDataController {

    @Value("${file.dir}")
    private String uploadPath;  // 업로드 경로

    
}

package com.playdata.miniproject.feed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feed")
public class FeedController {

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
}

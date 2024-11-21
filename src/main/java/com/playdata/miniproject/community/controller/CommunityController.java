package com.playdata.miniproject.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class CommunityController {
    @GetMapping("/")
    public String community() {
        return "community/community";
    }
    @GetMapping("/read")
    public String readcommunity() {
        return "community/read";
    }
}

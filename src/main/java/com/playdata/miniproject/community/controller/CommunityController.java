package com.playdata.miniproject.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commnunity")
public class CommunityController {

    @GetMapping("/")
    public String community() {
        return "/community/community";
    }
}

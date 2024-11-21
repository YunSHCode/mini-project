package com.playdata.miniproject.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class TestController {
    @GetMapping("/success")
    public String success() {
        return "user/success";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
}

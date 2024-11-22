package com.playdata.miniproject.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cafe")
public class CafeController {

    @GetMapping("/")
    public String feed() {
        return "/cafe/cafe";
    }

    @GetMapping("/read")
    public String readcafe() {
        return "cafe/cafe_particular";
    }
}

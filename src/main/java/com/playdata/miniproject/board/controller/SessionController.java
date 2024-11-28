package com.playdata.miniproject.board.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @GetMapping("/init-session")
        public String initializeSession(HttpSession session) {
            session.setAttribute("userKey", 1); // 예: 사용자 키

            return "Session initialized: " + session.getAttribute("userKey");
    }

}

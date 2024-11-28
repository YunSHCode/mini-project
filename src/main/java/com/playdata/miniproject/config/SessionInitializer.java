package com.playdata.miniproject.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SessionInitializer {
    @Bean
    public ApplicationRunner initializeSession() {
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            try {
                // 서버가 실행된 후 초기화 엔드포인트 호출
                String url = "http://localhost:8080/init-session"; // 서버 기본 포트에 맞게 URL 수정
                String response = restTemplate.getForObject(url, String.class);
                System.out.println("Session initialization response: " + response);
            } catch (Exception e) {
                System.err.println("Failed to initialize session: " + e.getMessage());
            }
        };
    }

}

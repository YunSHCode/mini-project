package com.playdata.miniproject.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.board.dir}")
    private String boardDir;

    @Value("${file.user.dir}")
    private String userDir;

    @Value("${file.feed.dir}")
    private String feedDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // board 디렉토리 매핑
        registry.addResourceHandler("/upload/board/**")
                .addResourceLocations("file:" + boardDir);

        // user 디렉토리 매핑
        registry.addResourceHandler("/upload/user/**")
                .addResourceLocations("file:" + userDir);

        // feed 디렉토리 매핑
        registry.addResourceHandler("/upload/feed/**")
                .addResourceLocations("file:" + feedDir);
    }
}
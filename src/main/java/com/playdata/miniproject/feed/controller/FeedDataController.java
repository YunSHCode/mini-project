package com.playdata.miniproject.feed.controller;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.service.FeedService;
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

    @Value("${file.dir}")
    private String UPLOADPATH; // "C:/feedImg/" 파일을 저장할 경로

    // 피드 업로드 및 파일 저장
    @PostMapping("/feed/upload")
    public String responseUpload(
            @RequestPart(value = "feedFile", required = false) MultipartFile[] uploadFiles, // 여러 개의 파일을 처리
            @RequestParam("content") String content) {

        try {
            // 피드 ID 생성 (여기서는 랜덤으로 생성한 예시)
            int feedId = generateFeedId();

            // FeedDTO 객체 생성 및 설정
            FeedDTO feed = new FeedDTO();
            feed.setFeedId(feedId);
            feed.setFeedContent(content);
            feed.setFeedCreateDt(LocalDateTime.now());
            feed.setFeedUpdateDt(LocalDateTime.now());

            // 파일이 있을 경우 처리
            List<FeedfileDTO> fileDTOList = new ArrayList<>();
            if (uploadFiles != null && uploadFiles.length > 0) {
                for (MultipartFile file : uploadFiles) {
                    // 파일 이름 고유하게 생성
                    String originalFileName = file.getOriginalFilename();
                    String fileName = UUID.randomUUID().toString() + "_" + originalFileName;

                    // 파일 저장 경로 설정
                    Path filePath = Paths.get(UPLOADPATH, fileName);
                    try {
                        file.transferTo(filePath.toFile()); // 파일 저장

                        // FeedfileDTO 생성 후 파일 목록에 추가
                        FeedfileDTO feedFile = new FeedfileDTO();
                        feedFile.setFeedFileName(fileName);

                        // MIME 타입을 기반으로 파일 유형을 설정
                        String contentType = file.getContentType();
                        if (contentType != null) {
                            if (contentType.startsWith("image/")) {
                                feedFile.setFeedFileType(1); // 이미지 파일
                            } else if (contentType.startsWith("application/")) {
                                feedFile.setFeedFileType(2); // 문서 파일
                            } else {
                                feedFile.setFeedFileType(3); // 기타 파일
                            }
                        } else {
                            feedFile.setFeedFileType(0); // 파일 유형 미정
                        }

                        feedFile.setFeedFileNameOrg(originalFileName);
                        fileDTOList.add(feedFile);

                    } catch (IOException e) {
                        log.error("파일 업로드 중 오류 발생 - 파일: {}", originalFileName, e);
                        return "파일 업로드 중 오류가 발생했습니다: " + originalFileName;
                    }
                }
            }

            // 피드와 파일 업로드 처리
            int uploadResult = feedService.FeedUpload(feed, fileDTOList);

            if (uploadResult > 0) {
                return "피드 업로드가 완료되었습니다!";
            } else {
                return "피드 업로드 실패";
            }

        } catch (Exception e) {
            log.error("알 수 없는 오류 발생", e);
            return "알 수 없는 오류가 발생했습니다.";
        }
    }

    // 피드 ID 생성 (예시로 랜덤 값을 사용)
    private int generateFeedId() {
        // 실제로는 DB에서 피드 ID를 자동 생성하는 방식 사용
        return (int) (Math.random() * 10000); // 랜덤 ID 예시
    }
}

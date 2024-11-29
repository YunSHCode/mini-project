package com.playdata.miniproject.feed.service;

import com.playdata.miniproject.feed.dao.FeedDAO;
import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class FeedService implements FeedServiceImp {
    @Override
    public int FeedUpload(FeedDTO feed, List<FeedfileDTO> files) {
        return 0;
    }

    @Override
    public void feedUpload(MultipartFile[] uploadFiles, String content, String[] diet) {

    }

    @Override
    public List<FeedDTO> feedList() {
        return List.of();
    }

    @Override
    public List<FeedDTO> search(String tag, String keyword) {
        return List.of();
    }

    @Override
    public int FeedUpdate(FeedDTO feed) {
        return 0;
    }

    @Override
    public int FeedDelete(int feed_id) {
        return 0;
    }
//
//    private final FeedDAO feedDAO;
//
//    @Value("${file.dir}")
//    private String UPLOAD_PATH;
//
//    @Value("${upload.path}") // application.properties에서 파일 경로를 읽어옵니다.
//    private String uploadPath;
//
//    @Override
//    public int FeedUpload(FeedDTO feed, FeedfileDTO feedFiles) {
//        // 피드 등록
//        int result = feedDAO.insertFeed(feed);
//        if (result > 0) {
//            // 파일 등록
//            feedDAO.insertFeedFiles(feedFiles); // 파일 정보 DB에 저장
//            return result; // 피드 등록 성공 시 결과 반환
//        }
//        return 0; // 피드 등록 실패 시 0 반환
//    }
//
//    @Override
//    public List<FeedDTO> feedList() {
//        return feedDAO.getAllFeeds(); // 전체 피드 목록 조회
//    }
//
//    @Override
//    public int FeedUpdate(FeedDTO feed) {
//        return feedDAO.updateFeed(feed); // 피드 수정
//    }
//
//    @Override
//    public int FeedDelete(int feedId) {
//        return feedDAO.deleteFeedById(feedId); // 피드 삭제
//    }
//
//    @Override
//    public List<FeedDTO> search(String tag, String keyword) {
//        return feedDAO.searchFeedsByTagAndKeyword(tag, keyword); // 태그와 키워드로 피드 검색
//    }
//
//
//    @Override
//    public int FeedUpload(FeedDTO feed, List<FeedfileDTO> files) {
//        return 0;
//    }
//
//    @Override
//    public void feedUpload(MultipartFile[] uploadFiles, String content, String[] diet) {
//
//        Arrays.stream(uploadFiles).forEach(uploadFile -> {
//            String originalName = uploadFile.getOriginalFilename(); // 파일의 저장이름
//            assert originalName != null; // null 일경우 에러 출력
//            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1); // 슬러시 문자제거
//
//            String uuid = UUID.randomUUID().toString(); // 랜덤 문자열 선언
//            String saveName = uuid + "_" + fileName; // 실제 저장 네임 지정
//            Path savePath = Paths.get(UPLOAD_PATH + File.separator + saveName); // 저장 위치 선언
//
//            try {
//                uploadFile.transferTo(savePath); // 실제 이미지 저장
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//    }
}

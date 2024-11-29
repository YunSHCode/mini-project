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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class FeedService implements FeedServiceImp {

    private final FeedDAO feedDAO;

    @Value("${upload.path}") // application.properties에서 파일 경로를 읽어옵니다.
    private String uploadPath;

    @Override
    public int FeedUpload(FeedDTO feed, List<FeedfileDTO> feedFiles) {
        // 피드 등록
        int result = feedDAO.insertFeed(feed);
        if (result > 0) {
            // 파일 등록
            if (feedFiles != null && !feedFiles.isEmpty()) {
                feedDAO.insertFeedFiles(feedFiles); // 파일 정보 DB에 저장
            }
            return result; // 피드 등록 성공 시 결과 반환
        }
        return 0; // 피드 등록 실패 시 0 반환
    }

    @Override
    public List<FeedDTO> feedList() {
        return feedDAO.getAllFeeds(); // 전체 피드 목록 조회
    }

    @Override
    public FeedDTO getFeedInfo(int feedId) {
        return feedDAO.getFeedById(feedId); // 특정 피드 조회
    }

    @Override
    public int FeedUpdate(FeedDTO feed) {
        return feedDAO.updateFeed(feed); // 피드 수정
    }

    @Override
    public int FeedDelete(int feedId) {
        return feedDAO.deleteFeedById(feedId); // 피드 삭제
    }

    @Override
    public List<FeedDTO> search(String keyword) {
        return feedDAO.searchFeedsByKeyword(keyword); // 키워드로 피드 검색
    }

    @Override
    public List<FeedDTO> search(String tag, String keyword) {
        return feedDAO.searchFeedsByTagAndKeyword(tag, keyword); // 태그와 키워드로 피드 검색
    }

    @Override
    public List<FeedfileDTO> getFileList(int feedId) {
        return feedDAO.getFeedFilesByFeedId(feedId); // 피드 ID에 해당하는 파일 목록 조회
    }

    @Override
    public FeedfileDTO getFile(int fileId) {
        return feedDAO.getFeedFileById(fileId); // 파일 ID로 파일 정보 조회
    }
}

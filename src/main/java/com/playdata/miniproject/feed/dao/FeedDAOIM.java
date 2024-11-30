package com.playdata.miniproject.feed.dao;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;

import java.util.List;

public interface FeedDAOIM {

    // 1. 피드 생성
    int insertFeed(FeedDTO feed);

    // 2. 첨부파일 저장
    int insertFeedFiles(FeedfileDTO feedFiles);

    // 3. 피드 목록 조회 (전체 피드 조회)
    List<FeedDTO> getAllFeeds();

    // 5. 피드 삭제 (feedId로 삭제)
    int deleteFeedById(int feedId);

    // 6. 피드 수정
    int updateFeed(FeedDTO feed);

    // 10. 태그와 키워드로 피드 검색
    List<FeedDTO> searchFeedsByTagAndKeyword(String tag, String keyword);
}

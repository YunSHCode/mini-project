package com.playdata.miniproject.feed.dao;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;

import java.util.List;

public interface FeedDAOIM {

    // 1. 피드 생성
    int insertFeed(FeedDTO feed);

    // 2. 첨부파일 저장
    int insertFeedFiles(List<FeedfileDTO> feedFiles);

    // 3. 피드 목록 조회 (전체 피드 조회)
    List<FeedDTO> getAllFeeds();

    // 4. 특정 피드 조회 (feedId로 조회)
    FeedDTO getFeedById(int feedId);

    // 5. 피드 삭제 (feedId로 삭제)
    int deleteFeedById(int feedId);

    // 6. 피드 수정
    int updateFeed(FeedDTO feed);

    // 7. 특정 피드의 파일 목록 조회 (feedId로 조회)
    List<FeedfileDTO> getFeedFilesByFeedId(int feedId);

    // 8. 특정 파일 조회 (fileId로 조회)
    FeedfileDTO getFeedFileById(int fileId);

    // 9. 검색 (키워드로 피드 검색)
    List<FeedDTO> searchFeedsByKeyword(String keyword);

    // 10. 태그와 키워드로 피드 검색
    List<FeedDTO> searchFeedsByTagAndKeyword(String tag, String keyword);
}

package com.playdata.miniproject.feed.service;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;

import java.util.List;

public interface FeedServiceImp {

        // 1. 피드 업로드
        int uploadFeed(int userKey, String feedContent, String feedTag, List<FeedfileDTO> files);

        // 2. 전체 피드 목록 조회
        List<FeedDTO> getAllFeeds();
        List<FeedfileDTO> getFeedFiles(int feedId);

        // 3. 특정 유저의 피드 목록 조회
        List<FeedDTO> getFeedsByUser(int userKey);

        // 4. 태그로 피드 목록 조회
        List<FeedDTO> getFeedsByTag(String feedTag);

        // 5. 피드 수정
        int updateFeed(FeedDTO feed);

        // 6. 피드 삭제
        int deleteFeed(int feedId);
}

package com.playdata.miniproject.feed.dao;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.mapper.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedDAO implements FeedDAOIM {

    private final FeedMapper mapper;

    // 1. 피드 생성하기
    @Override
    public int insertFeed(FeedDTO feed) {
        return mapper.insertFeed(feed);  // 피드 생성
    }

    // 2. 피드 파일 생성하기
    @Override
    public int insertFeedFiles(List<FeedfileDTO> feedFiles) {
        return mapper.insertFeedFiles(feedFiles);  // 여러 파일을 한 번에 저장
    }

    // 3. 피드 목록 조회 (전체 피드 조회)
    @Override
    public List<FeedDTO> getAllFeeds() {
        return mapper.getAllFeeds();  // 모든 피드를 반환 (파일 정보는 별도 처리)
    }

    //4. 피드 파일 조회
    @Override
    public List<FeedfileDTO> getFeedFiles(int feedId) {
        return mapper.getFeedFiles(feedId);
    }

    // 4. 피드 삭제하기
    @Override
    public int deleteFeedById(int feedId) {
        return mapper.deleteFeed(feedId);  // 피드 ID로 피드를 삭제
    }

    // 5. 피드 수정하기
    @Override
    public int updateFeed(FeedDTO feed) {
        return mapper.updateFeed(feed);  // 피드 수정
    }

    // 6. 피드 태그로 검색하기
    @Override
    public List<FeedDTO> getFeedsByTag(String feedTag) {
        return mapper.getFeedsByTag(feedTag);  // 태그로 피드 목록 조회
    }

    // 7. 유저의 피드 검색하기
    @Override
    public List<FeedDTO> getFeedsByUser(int userKey) {
        return mapper.getFeedsByUser(userKey);  // 특정 유저의 피드 목록 조회
    }
}

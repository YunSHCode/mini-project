package com.playdata.miniproject.feed.mapper;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {

    // 1. 전체 피드 조회
    List<FeedDTO> getAllFeeds();

    // 2. 태그로 피드 조회
    List<FeedDTO> getFeedsByTag(String feedTag);
    List<FeedfileDTO> getFeedFiles(int feedId);

    // 3. 특정 유저의 피드 조회
    List<FeedDTO> getFeedsByUser(int userKey);

    // 4. 피드 생성
    int insertFeed(FeedDTO feed);

    // 5. 피드 파일 생성
    int insertFeedFiles(List<FeedfileDTO> feedFiles); // 여러 파일을 처리할 수 있도록 변경

    // 6. 피드 수정
    int updateFeed(FeedDTO feed);

    // 7. 피드 삭제
    int deleteFeed(int feedId);
}

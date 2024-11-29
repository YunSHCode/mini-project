package com.playdata.miniproject.feed.mapper;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {

    // feed 조회
    List<FeedDTO> allFeed();

    // 태그 조건 조회
    FeedDTO FeedByTag(String feedTag);

    // 나의 조건 조회
    FeedDTO feedByMy(int userKey);

    // feed 생성
    int insertFeed(FeedDTO feed);

    // feed 파일 생성
    int insertFeedFiles(FeedfileDTO feed);

    // feed 수정
    int editFeed(FeedDTO feed);

    // feed 삭제
    int deleteFeed(int feedId);

}
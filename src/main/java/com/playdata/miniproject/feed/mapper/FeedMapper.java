package com.playdata.miniproject.feed.mapper;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    // Feed 생성
    int insertFeed(FeedDTO feed);

    // Feed 파일 생성
    int insertFeedFiles(List<FeedfileDTO> feedFiles);

    // 모든 Feed 조회
    List<FeedDTO> selectAllFeeds();

    // 특정 Feed 조회
    FeedDTO readFeed(int feedId);

    // Feed 삭제
    int deleteFeed(int feedId);

    // 특정 Feed의 파일 목록 조회
    List<FeedfileDTO> selectFeedFiles(int feedId);

    int updateFeed(FeedDTO feed);

    List<FeedDTO> searchFeeds(String keyword);

    List<FeedDTO> searchFeedsByTag(String tag, String keyword);

    FeedfileDTO selectFeedFile(int fileId);
}

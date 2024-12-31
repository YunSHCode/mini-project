package com.playdata.miniproject.feed.dao;
import com.playdata.miniproject.feed.dto.FeedCommentsDTO;
import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedListDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

public interface FeedDAOIM {

    // 1. 피드 생성하기
    int insertFeed(FeedDTO feed);

    // 2. 피드 파일 생성하기
    int insertFeedFiles(List<FeedfileDTO> feedFiles); // 파일 목록을 받도록 수정

    // 3. 피드 목록 조회 (전체 피드 조회)
    List<FeedListDTO> getAllFeeds(); // FeedDTO와 FeedfileDTO를 따로 받지 않도록 수정

    // 4. 피드 파일 목록 조회
    List<FeedfileDTO> getFeedFiles(int feedId);

    // 4. 피드 삭제하기
    int deleteFeedById(int feedId);

    // 5. 피드 수정하기
    int updateFeed(FeedDTO feed);

    // 6. 피드 태그 검색하기
    List<FeedDTO> getFeedsByTag(String feedTag); // FeedDTO와 FeedfileDTO를 따로 받지 않도록 수정

    //###################
    //      MyFeed
    //##################

    // 7. 나의 피드 리스트 조회하기
    List<FeedListDTO> getFeedsByUser(
            @SessionAttribute("userKey") int userKey);  // 여러 피드가 있을 수 있기 때문에 List로 반환

    // 나의 피드 검색하기
    int myFeedCount(
            @SessionAttribute("userKey") int userKey
    );

    int deleteFeedCommentByFeedId(int feedId);

    FeedListDTO getFeedById(int feedId);
  
    int insertComment(FeedCommentsDTO comment);
    List<FeedCommentsDTO> getCommentsByFeedId(Integer feedId);
}

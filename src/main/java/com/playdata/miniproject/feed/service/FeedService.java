package com.playdata.miniproject.feed.service;

import com.playdata.miniproject.feed.dao.FeedDAO;
import com.playdata.miniproject.feed.dto.FeedCommentsDTO;
import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedListDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedService implements FeedServiceImp {

    private final FeedDAO feedDAO; // 의존성 주입


    // 2. 피드 업로드 (userKey, feedContent, feedTag로 피드 등록)
    @Override
    public int uploadFeed(int userKey, String feedContent, String feedTag, List<FeedfileDTO> files) {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("피드는 최소 1개의 파일을 포함해야 합니다.");
        }

        // 피드 객체 생성
        FeedDTO feed = new FeedDTO(userKey, feedContent, feedTag);

        // 피드 등록
        // feedDAO.insertFeed(feed);
        feedDAO.insertFeed(feed);
        int feed_id = feed.getFeedId();

        List<FeedfileDTO> files2 = files.stream().map(o -> {
            o.setFeedId(feed_id);
            return o;
        }).collect(Collectors.toList());

        // 파일 등록
        // feedDAO.insertFeedFiles(files);
        feedDAO.insertFeedFiles(files2);

        // 피드 ID 반환
        return feed.getFeedId();
    }

    // 3. 전체 피드 목록 조회
    @Override
    public List<FeedListDTO> getAllFeeds() {
        return feedDAO.getAllFeeds(); // 모든 피드 목록 조회 (DAO에서 호출)
    }

    // 4. 피드 파일 목록 조회
    @Override
    public List<FeedfileDTO> getFeedFiles(int feedId) {
        return  feedDAO.getFeedFiles(feedId);
    }


    // 4. 특정 유저의 피드 목록 조회
    @Override
    public List<FeedDTO> getFeedsByUser(int userKey) {
        return feedDAO.getFeedsByUser(userKey); // 해당 유저의 피드 목록 조회
    }

    // 5. 태그로 피드 목록 조회
    @Override
    public List<FeedDTO> getFeedsByTag(String feedTag) {
        return feedDAO.getFeedsByTag(feedTag); // 해당 태그가 포함된 피드 목록 조회
    }

    // 6. 피드 수정
    @Override
    public int updateFeed(FeedDTO feed) {
        return feedDAO.updateFeed(feed); // 피드 수정 (DAO에서 호출)
    }

    // 7. 피드 삭제
    @Override
    public int deleteFeed(int feedId) {
        return feedDAO.deleteFeedById(feedId); // 피드 삭제 (DAO에서 호출)
    }

    @Override
    public int deleteFeedCommentByFeedId(int feedId) {

        return feedDAO.deleteFeedCommentByFeedId(feedId);
    }

    public List<FeedCommentsDTO> getCommet(int feedId) {
        return feedDAO.getCommentsByFeedId(feedId);
    }
}

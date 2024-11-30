package com.playdata.miniproject.feed.dao;

import com.playdata.miniproject.board.mapper.BoardMapper;
import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.mapper.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FeedDAO implements FeedDAOIM {

    @Autowired
    FeedMapper mapper;

    @Override
    public int insertFeed(FeedDTO feed) {
        return mapper.insertFeed(feed);
    }

    @Override
    public int insertFeedFiles(FeedfileDTO feedFiles) {
        return mapper.insertFeedFiles(feedFiles);
    }

    @Override
    public List<FeedDTO> getAllFeeds() {
        return List.of();
    }

    @Override
    public int deleteFeedById(int feedId) {
        return mapper.deleteFeed(feedId);
    }

    @Override
    public int updateFeed(FeedDTO feed) {
        return mapper.editFeed(feed);
    }

    @Override
    public List<FeedDTO> searchFeedsByTagAndKeyword(String tag, String keyword) {
        return List.of();
    }
}

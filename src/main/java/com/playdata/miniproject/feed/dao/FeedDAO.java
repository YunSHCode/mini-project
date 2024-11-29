package com.playdata.miniproject.feed.dao;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.mapper.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FeedDAO implements FeedDAOIM {

    private static final Logger logger = LoggerFactory.getLogger(FeedDAO.class);
    private final FeedMapper mapper;

    @Override
    public int insertFeed(FeedDTO feed) {
        try {
            return mapper.insertFeed(feed);  // 피드 삽입
        } catch (DataAccessException e) {
            logger.error("Failed to insert feed: {}", feed, e);
            throw new RuntimeException("Failed to insert feed", e);  // 예외를 던져 호출자가 처리하도록
        }
    }

    @Override
    public int insertFeedFiles(List<FeedfileDTO> feedFiles) {
        try {
            return mapper.insertFeedFiles(feedFiles);  // 피드 파일 삽입
        } catch (DataAccessException e) {
            logger.error("Failed to insert feed files: {}", feedFiles, e);
            throw new RuntimeException("Failed to insert feed files", e);
        }
    }

    @Override
    public List<FeedDTO> getAllFeeds() {
        try {
            return Optional.ofNullable(mapper.selectAllFeeds())
                    .orElseThrow(() -> new RuntimeException("No feeds found"));  // Null 처리 추가
        } catch (DataAccessException e) {
            logger.error("Failed to fetch all feeds", e);
            throw new RuntimeException("Failed to fetch all feeds", e);
        }
    }

    @Override
    public FeedDTO getFeedById(int feedId) {
        try {
            FeedDTO feed = mapper.readFeed(feedId);
            if (feed == null) {
                throw new RuntimeException("Feed not found with id: " + feedId);
            }
            return feed;
        } catch (DataAccessException e) {
            logger.error("Failed to fetch feed by id: {}", feedId, e);
            throw new RuntimeException("Failed to fetch feed by id", e);
        }
    }

    @Override
    public int deleteFeedById(int feedId) {
        try {
            return mapper.deleteFeed(feedId);  // 피드 삭제
        } catch (DataAccessException e) {
            logger.error("Failed to delete feed by id: {}", feedId, e);
            throw new RuntimeException("Failed to delete feed by id", e);
        }
    }

    @Override
    public int updateFeed(FeedDTO feed) {
        try {
            return mapper.updateFeed(feed);  // 피드 수정
        } catch (DataAccessException e) {
            logger.error("Failed to update feed: {}", feed, e);
            throw new RuntimeException("Failed to update feed", e);
        }
    }

    @Override
    public List<FeedDTO> searchFeedsByKeyword(String keyword) {
        try {
            return Optional.ofNullable(mapper.searchFeeds(keyword))
                    .orElseThrow(() -> new RuntimeException("No feeds found for keyword: " + keyword));
        } catch (DataAccessException e) {
            logger.error("Failed to search feeds by keyword: {}", keyword, e);
            throw new RuntimeException("Failed to search feeds by keyword", e);
        }
    }

    @Override
    public List<FeedDTO> searchFeedsByTagAndKeyword(String tag, String keyword) {
        try {
            return Optional.ofNullable(mapper.searchFeedsByTag(tag, keyword))
                    .orElseThrow(() -> new RuntimeException("No feeds found for tag: " + tag + " and keyword: " + keyword));
        } catch (DataAccessException e) {
            logger.error("Failed to search feeds by tag and keyword: {}, {}", tag, keyword, e);
            throw new RuntimeException("Failed to search feeds by tag and keyword", e);
        }
    }

    @Override
    public List<FeedfileDTO> getFeedFilesByFeedId(int feedId) {
        try {
            return Optional.ofNullable(mapper.selectFeedFiles(feedId))
                    .orElseThrow(() -> new RuntimeException("No files found for feed id: " + feedId));
        } catch (DataAccessException e) {
            logger.error("Failed to fetch feed files for feed id: {}", feedId, e);
            throw new RuntimeException("Failed to fetch feed files by feed id", e);
        }
    }

    @Override
    public FeedfileDTO getFeedFileById(int fileId) {
        try {
            FeedfileDTO feedfile = mapper.selectFeedFile(fileId);
            if (feedfile == null) {
                throw new RuntimeException("File not found with id: " + fileId);
            }
            return feedfile;
        } catch (DataAccessException e) {
            logger.error("Failed to fetch feed file by id: {}", fileId, e);
            throw new RuntimeException("Failed to fetch feed file by id", e);
        }
    }
}

package com.playdata.miniproject.feed.service;


import com.playdata.miniproject.feed.dao.FeedDAO;
import com.playdata.miniproject.feed.dto.FeedCommentsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FeedCommentService {

    final private FeedDAO feedDAO;


    public int insertComment(FeedCommentsDTO comments) {
        return feedDAO.insertComment(comments);
    }

    public List<FeedCommentsDTO> getCommentsByFeedId(Integer feedId) {
        return feedDAO.getCommentsByFeedId(feedId);
    }
}

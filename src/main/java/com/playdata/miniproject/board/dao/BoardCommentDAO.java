package com.playdata.miniproject.board.dao;

import com.playdata.miniproject.board.dto.BoardCommentRequest;
import com.playdata.miniproject.board.dto.BoardCommentResponse;

import java.util.List;

public interface BoardCommentDAO {
    void saveComment(BoardCommentRequest commentRequest);

    List<BoardCommentResponse> getCommentsByBoardId(int boardId);

    void updateCommentGroupId(int commentId1);
}

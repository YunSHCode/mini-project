package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dto.BoardCommentRequest;
import com.playdata.miniproject.board.dto.BoardCommentResponse;

import java.util.List;

public interface BoardCommentService {
    void saveComment(BoardCommentRequest commentRequest);

    List<BoardCommentResponse> getCommentsByBoardId(int boardId);
}

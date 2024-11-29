package com.playdata.miniproject.board.dao;

import com.playdata.miniproject.board.dto.BoardCommentRequest;
import com.playdata.miniproject.board.dto.BoardCommentResponse;
import com.playdata.miniproject.board.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCommentDAOImpl implements BoardCommentDAO{

    private final BoardCommentMapper mapper;

    @Override
    public void saveComment(BoardCommentRequest comment) {
        System.out.println(comment.getCommentContent());
        mapper.saveComment(comment);
    }

    @Override
    public List<BoardCommentResponse> getCommentsByBoardId(int boardId) {
        return mapper.commentList(boardId);
    }

    @Override
    public void updateCommentGroupId(int commentId) {
        mapper.updateCommentGroupId(commentId);
    }
}

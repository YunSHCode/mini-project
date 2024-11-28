package com.playdata.miniproject.board.mapper;

import com.playdata.miniproject.board.dto.BoardCommentRequest;
import com.playdata.miniproject.board.dto.BoardCommentResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardCommentMapper {
    void saveComment(BoardCommentRequest comment);

    List<BoardCommentResponse> commentList(int boardId);

    void updateCommentGroupId(int commentId);
}

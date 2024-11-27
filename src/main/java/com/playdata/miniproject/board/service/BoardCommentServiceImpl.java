package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dao.BoardCommentDAO;
import com.playdata.miniproject.board.dto.BoardCommentRequest;
import com.playdata.miniproject.board.dto.BoardCommentResponse;
import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService{
    private final BoardCommentDAO boardCommentDAO;
    @Override
    public void saveComment(BoardCommentRequest commentRequest) {
        if ((Integer)commentRequest.getCommentGroup() == 0) {
            // 원본 댓글 작성 로직
            commentRequest.setCommentStep(0);
            commentRequest.setCommentGroup(-1);
            boardCommentDAO.saveComment(commentRequest); // 먼저 댓글을 저장합니다.
            // 댓글 저장 후 해당 댓글의 ID를 가져와 그룹 ID로 업데이트
            int commentId = commentRequest.getCommentId(); // 저장 후 생성된 commentId를 가져옴
            boardCommentDAO.updateCommentGroupId(commentId); // 그룹 ID를 자신의 ID로 업데이트
        } else {
            // 답글 작성 로직
            commentRequest.setCommentStep(1);
            boardCommentDAO.saveComment(commentRequest); // 답글은 상위 그룹 ID를 포함하여 저장
        }
    }

    @Override
    public List<BoardCommentResponse> getCommentsByBoardId(int boardId) {
        return boardCommentDAO.getCommentsByBoardId(boardId);
    }
}

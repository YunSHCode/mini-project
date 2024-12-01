package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.util.Pagination;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardService {
    List<BoardWithUserDTO> readBoard();

    void insertBoard(String boardTitle, String boardContent, int userId);

    BoardWithUserDTO getBoardById(int id);

    Map<String, Object> getBoardList(int page, String searchCategory, String searchKeyword);
    void deleteBoard(int id);

    // 세션 유저와 게시글 게시자가 같은지 확인
    boolean checkWriter(int id, int userKey);

    void updateBoard(String boardTitle, String boardContent, int id);

    Page<BoardWithUserDTO> getBoardByUser(int userKey, int boardPage, int boardSize);
}

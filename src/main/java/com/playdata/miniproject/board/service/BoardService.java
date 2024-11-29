package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.util.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardService {
    public List<BoardWithUserDTO> readBoard();

    public void insertBoard(String boardTitle, String boardContent, int userId);

    public BoardWithUserDTO getBoardById(int id);

    public Map<String, Object> getBoardList(int page, String searchCategory, String searchKeyword);
    public void deleteBoard(int id);
}

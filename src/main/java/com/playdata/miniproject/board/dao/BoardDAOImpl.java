package com.playdata.miniproject.board.dao;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO{

    @Autowired
    BoardMapper mapper;

    @Override
    public List<BoardWithUserDTO> readBoard() {
        return mapper.readBoard();
    }

    @Override
    public void insertBoard(String boardTitle, String boardContent, int userId) {
        mapper.insertBoard(boardTitle, boardContent, userId);
    }

    @Override
    public BoardWithUserDTO readBoardById(int id) {
        return mapper.readBoardById(id);
    }

    @Override
    public int getTotalSearchCount(String category, String keyword) {
        return mapper.getTotalSearchCount(category, keyword);
    }

    @Override
    public List<BoardWithUserDTO> searchBoards(String category, String keyword, int offset, int limit) {
        return mapper.searchBoards(category, keyword, offset, limit);
    }

    @Override
    public int getTotalBoardsCount() {
        return mapper.getTotalBoardsCount();
    }

    @Override
    public List<BoardWithUserDTO> getBoards(int offset, int limit) {
        return mapper.getBoards(offset, limit);
    }

    @Override
    public void deleteBoard(int id) {
        mapper.deleteboard(id);
    }
}

package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dto.BoardDTO;
import com.playdata.miniproject.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public List<BoardDTO> readBoard() {
        return boardMapper.readBoard();
    }

    public void insertBoard(String boardTitle, String boardContent, Long userId) {
        boardMapper.insertBoard(boardTitle, boardContent, userId);
    }
}

package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dao.BoardDAO;
import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDAO boardDAO;

    public List<BoardWithUserDTO> readBoard() {
        return boardDAO.readBoard();
    }

    public void insertBoard(String boardTitle, String boardContent, int userId) {
        boardDAO.insertBoard(boardTitle, boardContent, userId);
    }

    public BoardWithUserDTO getBoardById(int id) {
        return boardDAO.readBoardById(id);
    }

    public Map<String, Object> getBoardList(int page, String searchCategory, String searchKeyword) {
        // Pagination 설정
        Pagination pagination = new Pagination();
        pagination.setCurrentPageNo(page);
        pagination.setRecordCountPerPage(10);
        pagination.setPageSize(10);

        // 검색 여부에 따라 데이터 수 계산
        int totalRecords = (searchKeyword != null && !searchKeyword.isEmpty())
                ? boardDAO.getTotalSearchCount(searchCategory, searchKeyword)
                : boardDAO.getTotalBoardsCount();
        pagination.setTotalRecordCount(totalRecords);
        pagination.calculatePagination();

        // 게시글 목록 조회
        List<BoardWithUserDTO> boardList = (searchKeyword != null && !searchKeyword.isEmpty())
                ? boardDAO.searchBoards(searchCategory, searchKeyword, pagination.getFirstRecordIndex(), pagination.getRecordCountPerPage())
                : boardDAO.getBoards(pagination.getFirstRecordIndex(), pagination.getRecordCountPerPage());

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("boardList", boardList);
        response.put("pagination", pagination);
        return response;
    }

    @Override
    public void deleteBoard(int id) {
        boardDAO.deleteBoard(id);
    }
}

package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dto.BoardDTO;
import com.playdata.miniproject.board.mapper.BoardMapper;
import com.playdata.miniproject.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public List<BoardDTO> readBoard() {
        return boardMapper.readBoard();
    }

    public void insertBoard(String boardTitle, String boardContent, int userId) {
        boardMapper.insertBoard(boardTitle, boardContent, userId);
    }

    public BoardDTO getBoardById(int id) {
        return boardMapper.readBoardById(id);
    }

    public Map<String, Object> getBoardList(int page, String searchCategory, String searchKeyword) {
        // Pagination 객체 생성
        Pagination pagination = new Pagination();
        pagination.setCurrentPageNo(page);
        pagination.setRecordCountPerPage(10); // 한 페이지당 10개 데이터
        pagination.setPageSize(10); // 페이지 리스트 크기

        // 검색 여부에 따라 전체 데이터 수 계산
        int totalRecords = (searchKeyword != null && !searchKeyword.isEmpty())
                ? boardMapper.getSearchCount(searchCategory, searchKeyword)
                : boardMapper.getTotalCount();
        pagination.setTotalRecordCount(totalRecords);

        // 페이징 계산
        pagination.calculatePagination();

        // 게시글 목록 조회
        List<BoardDTO> boardList = (searchKeyword != null && !searchKeyword.isEmpty())
                ? boardMapper.searchBoards(
                searchCategory,
                searchKeyword,
                pagination.getFirstRecordIndex(),
                pagination.getRecordCountPerPage()
        )
                : boardMapper.getBoards(
                pagination.getFirstRecordIndex(),
                pagination.getRecordCountPerPage()
        );

        // 응답 생성
        Map<String, Object> response = new HashMap<>();
        response.put("boardList", boardList);
        response.put("pagination", pagination); // Pagination 객체를 직접 전달
        return response;
    }
}

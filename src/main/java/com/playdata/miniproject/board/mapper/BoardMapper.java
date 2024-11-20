package com.playdata.miniproject.board.mapper;

import com.playdata.miniproject.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface BoardMapper {


    List<BoardDTO> readBoard();


    void insertBoard(@Param("boardTitle") String boardTitle,
                     @Param("boardContent") String boardContent,
                     @Param("userId") int userId);


    BoardDTO readBoardById(@Param("id") int id);

    int getTotalSearchCount(String searchCategory, String searchKeyword);

    List<BoardDTO> searchBoardsWithPagination(String searchCategory, String searchKeyword, int recordCountPerPage, int offset);

    int getTotalBoardCount();

    List<BoardDTO> readBoardWithPagination(int recordCountPerPage, int offset);
}

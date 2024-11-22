package com.playdata.miniproject.board.mapper;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface BoardMapper {


    List<BoardWithUserDTO> readBoard();


    void insertBoard(@Param("boardTitle") String boardTitle,
                     @Param("boardContent") String boardContent,
                     @Param("userId") int userId);


    BoardWithUserDTO readBoardById(@Param("id") int id);

    // 검색된 게시글 총 개수
    int getTotalSearchCount(@Param("category") String category, @Param("keyword") String keyword);

    // 검색된 게시글 조회
    List<BoardWithUserDTO> searchBoards(
            @Param("category") String category,
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit);

    // 전체 게시글 총 개수
    int getTotalBoardsCount();

    // 게시글 조회
    List<BoardWithUserDTO> getBoards(@Param("offset") int offset, @Param("limit") int limit);
}

package com.playdata.miniproject.board.mapper;

import com.playdata.miniproject.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM board")
    List<BoardDTO> readBoard();

    @Insert("INSERT INTO board (board_title, board_content, user_id, board_create_dt, board_update_dt) " +
            "VALUES (#{boardTitle}, #{boardContent}, #{userId}, NOW(), NOW())")
    void insertBoard(@Param("boardTitle") String boardTitle,
                     @Param("boardContent") String boardContent,
                     @Param("userId") Long userId);

}

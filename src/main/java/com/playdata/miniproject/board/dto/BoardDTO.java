package com.playdata.miniproject.board.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private int boardId;          // PK
    private String boardTitle;     // 제목
    private String boardContent;   // 내용
    private LocalDateTime boardCreateDt; // 생성 날짜
    private LocalDateTime boardUpdateDt; // 수정 날짜
    private int userId;           // 작성자 (FK)
}

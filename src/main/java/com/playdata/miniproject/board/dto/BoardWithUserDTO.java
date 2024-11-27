package com.playdata.miniproject.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardWithUserDTO {
    private Long boardId;          // PK
    private String boardTitle;     // 제목
    private String boardContent;   // 내용
    private LocalDateTime boardCreateDt; // 생성 날짜
    private LocalDateTime boardUpdateDt; // 수정 날짜
    private int userKey;           // 작성자 (FK)
    private String userRealname;       // 작성자 이름
}
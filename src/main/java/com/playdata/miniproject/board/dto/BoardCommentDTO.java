package com.playdata.miniproject.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardCommentDTO {
    private int commentId;
    private int commentGroup;
    private String commentContent;
    private int commentStep;
    private LocalDateTime commentCreateDt;
    private LocalDateTime commentUpdateDt;
    private int boardId;
}

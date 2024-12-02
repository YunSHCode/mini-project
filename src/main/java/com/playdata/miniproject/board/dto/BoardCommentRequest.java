package com.playdata.miniproject.board.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("boardCommentRequest")
public class BoardCommentRequest {
    private int commentId;
    private int boardId;
    private String commentContent;
    private int userKey;
    private int commentGroup;
    private int commentStep;
}

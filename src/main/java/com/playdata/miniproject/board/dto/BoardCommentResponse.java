package com.playdata.miniproject.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("commentResponse")
public class BoardCommentResponse {
    private int commentId;
    private String userRealname;
    private String commentCreateDt;
    private String commentContent;
}

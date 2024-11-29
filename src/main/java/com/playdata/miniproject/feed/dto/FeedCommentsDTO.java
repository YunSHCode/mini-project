package com.playdata.miniproject.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("feedComments")
public class FeedCommentsDTO {
    int feedCommentId;
    int userKey;
    int feedId;
    int feedCommentGroup;
    int feedCommentStep;
    String feedCommentContent;
    LocalDateTime feedCommentCreateDt;
    LocalDateTime feedCommentUpdateDt;
}






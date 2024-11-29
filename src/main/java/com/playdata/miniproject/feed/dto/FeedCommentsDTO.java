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
    int feed_comment_id;
    int user_key;
    int feed_comment_step;
    int feed_comment_group;
    LocalDateTime feed_comment_create_dt;
    LocalDateTime feed_comment_update_dt;
}






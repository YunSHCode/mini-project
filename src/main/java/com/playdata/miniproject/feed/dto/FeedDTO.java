package com.playdata.miniproject.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("feed")
public class FeedDTO {
    int feedId;
    int userKey;
    int feedCommentId;
    int feedFileId;
    String feedContent;
    String feedTag;
    LocalDateTime feedCreateDt;
    LocalDateTime feedUpdateDt;
    List<MultipartFile> files;
}

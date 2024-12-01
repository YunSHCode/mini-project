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
    int feedId; // 피드 고유 아이디
    int userKey; // 유저 고유 아이디
    String feedContent; // 피드 내용
    LocalDateTime feedCreateDt; // 피드 생성 날짜
    LocalDateTime feedUpdateDt; // 피드 수정 날짜
    String feedTag; // 피드 태그
    List<MultipartFile> feedImages;
    List<FeedfileDTO> feedFiles;

    public FeedDTO(int userKey, String feedContent, String feedTag) {
        this.userKey = userKey;
        this.feedContent = feedContent;
        this.feedTag = feedTag;
    }



}

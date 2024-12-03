package com.playdata.miniproject.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("feedList")
public class FeedListDTO {
    // feed id
    int feedId;
    // user.info
    int userKey;
    String userNickName;
    String userVeganType;
    String userProfilePictureGenerated;
    // feed contents
    String feedContent;
    String feedTag;
    LocalDateTime feedCreateDt; // 피드 생성 날짜
    LocalDateTime feedUpdateDt; // 피드 수정 날짜
    List<FeedfileDTO> feedFiles;
    List<String> feedImageList;

    public FeedListDTO(
            int feedId,
            int userKey,
            String userNickName,
            String userVeganType,
            String feedContent,
            String feedTag,
            String userProfilePictureGenerated,
    LocalDateTime feedUpdateDt
    ) {
        this.feedId = feedId;
        this.userNickName = userNickName;
        this.userVeganType = userVeganType;
        this.userKey = userKey;
        this.feedContent = feedContent;
        this.feedTag = feedTag;
        this.userProfilePictureGenerated = userProfilePictureGenerated;
        this.feedUpdateDt = feedUpdateDt;
    }

}

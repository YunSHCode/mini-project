package com.playdata.miniproject.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("FeedFile")
public class FeedfileDTO {
    private int feedFileId;
    private int feedFileType;
    private String feedFileNameOrg;  // 원본 파일 이름
    private String feedFileName;     // 저장된 파일 이름
    private LocalDateTime feedUploadDt;

    // 파일 이름 설정 메소드
    public void setFeedFileName(String fileName) {
        this.feedFileName = fileName;
    }

    // 원본 파일 이름 설정 메소드
    public void setFeedFileNameOrg(String originalFileName) {
        this.feedFileNameOrg = originalFileName;
    }
}

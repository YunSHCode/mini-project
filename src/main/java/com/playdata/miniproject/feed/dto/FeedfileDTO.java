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
    int feedFileId; // 피드 파일 고유 아이디
    int feedFileType; // 피드 파일 타입 (int로 유지)
    String feedFileNameOrg; // 파일 원본 이름
    String feedFileName; // 파일 데이터
    LocalDateTime feedUploadDt; // 피드 업로드 날짜
    int feedId; // 피드 외래키
}

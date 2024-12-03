package com.playdata.miniproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFileDTO {

    private String userProfilePictureOriginal;         // 프로필 사진 URL 원본 파일명
    private String userProfilePictureGenerated;
}

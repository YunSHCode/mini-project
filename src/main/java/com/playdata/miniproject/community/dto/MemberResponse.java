package com.playdata.miniproject.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    int userKey;       // 멤버 ID
    String userNickname;  // 멤버 닉네임
    String userProfilePictureGenerated; // 멤버 사진
}
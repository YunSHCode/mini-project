package com.playdata.miniproject.community.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupListResponse {
    private int communityId; // 모임 ID
    private String communityTitle; // 모임 제목
    private String communityPictureGenerated; // 모임 사진
    private int communityMember; // 현재 인원
    private int communityMemberMax; // 최대 인원
}

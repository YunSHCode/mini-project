package com.playdata.miniproject.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private int userKey; // 사용자 키
    private int communityId; // 그룹 ID
    private String memberStatus; // 멤버 상태 (예: "creator", "member")
}

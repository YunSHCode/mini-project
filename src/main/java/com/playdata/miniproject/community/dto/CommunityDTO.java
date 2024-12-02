package com.playdata.miniproject.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("community")
public class CommunityDTO {
    private int communityId;
    private String communityTitle;
    private String communityContents;
    private int communityMember=1;
    private int communityMemberMax=10;
    private LocalDateTime communityCreateDt;
    private LocalDateTime communityUpdateDt;
    private String communityPictureOriginal;
    private String communityPictureGenerated;
    private int userKey;
}

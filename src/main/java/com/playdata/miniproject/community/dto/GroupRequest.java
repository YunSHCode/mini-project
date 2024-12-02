package com.playdata.miniproject.community.dto;

import lombok.Data;

@Data
public class GroupRequest {
    private int communityId;
    private String communityTitle;
    private String communityContents;
    private int communityMemberMax;
    private String communityPictureOriginal;
    private String communityPictureGenerated;
    private int userKey;
}

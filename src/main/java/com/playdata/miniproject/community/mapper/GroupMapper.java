package com.playdata.miniproject.community.mapper;


import com.playdata.miniproject.community.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {
    void insertGroup(GroupRequest groupRequest);

    List<GroupListResponse> getCommunityList(@Param("offset") int offset,@Param("limit") int size);

    int countCommunity();

    GroupDetailResponse getCommunityDetail(@Param("communityId") int groupId);

    List<MemberResponse> getGroupMembers(@Param("communityId") int groupId);

    void insertMember(@Param("member") MemberRequest memberRequest);

    int isMemberAlreadyRequested(int userKey, int communityId);
}

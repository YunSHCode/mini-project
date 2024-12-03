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

    List<GroupListResponse> findMyCreatedGroups(@Param("userKey") int userKey,@Param("offset") int offset, @Param("size") int size);

    int countMyCreatedGroups(@Param("userKey") int userKey);

    void updateCommunity(GroupRequest groupRequest);

    void deleteCommunity(int id);

    List<MemberResponse> getPendingMembers(@Param("communityId") int groupId);

    void approveMember(@Param("communityId") int communityId, @Param("userKey") int userKey);
    void increaseCommunityMemberCount(@Param("communityId") int communityId);
    void removeMember(@Param("communityId") int communityId, @Param("userKey") int userKey);
    void decreaseCommunityMemberCount(@Param("communityId") int communityId);
    List<GroupListResponse> findMyGroups(@Param("userKey") int userKey,@Param("memberStatus") String memberStatus,@Param("offset") int offset, @Param("size") int size);

    int countMyGroups(@Param("userKey") int userKey,@Param("memberStatus") String memberStatus);
}

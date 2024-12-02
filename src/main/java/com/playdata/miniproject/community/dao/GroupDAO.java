package com.playdata.miniproject.community.dao;

import com.playdata.miniproject.community.dto.*;

import java.util.List;

public interface GroupDAO {
    void insertCommunity(GroupRequest groupRequest);

    List<GroupListResponse> getCommunityList(int offset, int size);

    int countCommunity();

    GroupDetailResponse getCommunityDetail(int groupId);

    List<MemberResponse> getGroupMembers(int groupId);

    void insertMember(MemberRequest member);

    boolean isMemberAlreadyRequested(int userKey, int communityId);

    List<GroupListResponse> findMyCreatedGroups(int userKey, int offset, int size);

    int countMyCreatedGroups(int userKey);

    void updateCommunity(GroupRequest groupRequest);

    void deleteCommunity(int id);
}

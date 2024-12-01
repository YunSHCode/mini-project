package com.playdata.miniproject.community.service;

import com.playdata.miniproject.community.dto.GroupDetailResponse;
import com.playdata.miniproject.community.dto.GroupListResponse;
import com.playdata.miniproject.community.dto.GroupRequest;
import com.playdata.miniproject.community.dto.MemberResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GroupService {
    void insertCommunity(GroupRequest groupRequest);

    Page<GroupListResponse> getCommunityList(int groupPage, int groupSize);

    GroupDetailResponse getCommunityDetail(int groupId);

    List<MemberResponse> getGroupMembers(int groupId);

    boolean isMemberAlreadyRequested(int userKey, int communityId);

    void requestToJoin(int userKey, int communityId);
}

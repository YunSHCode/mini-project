package com.playdata.miniproject.community.service;

import com.playdata.miniproject.community.dto.GroupDetailResponse;
import com.playdata.miniproject.community.dto.GroupListResponse;
import com.playdata.miniproject.community.dto.GroupRequest;
import com.playdata.miniproject.community.dto.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GroupService {
    void insertCommunity(GroupRequest groupRequest);

    Page<GroupListResponse> getCommunityList(int groupPage, int groupSize);

    GroupDetailResponse getCommunityDetail(int groupId);

    List<MemberResponse> getGroupMembers(int groupId);

    boolean isMemberAlreadyRequested(int userKey, int communityId);

    void requestToJoin(int userKey, int communityId);

    Page<GroupListResponse> findMyCreatedGroups(int userKey, int groupPage, int groupSize);

    void updateCommunity(GroupRequest groupRequest, String oldFileName);

    void deleteCommunity(int id);
}

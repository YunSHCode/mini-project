package com.playdata.miniproject.community.service;

import com.playdata.miniproject.community.dao.GroupDAO;
import com.playdata.miniproject.community.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{
    private final GroupDAO groupDAO;

    @Override
    @Transactional
    public void insertCommunity(GroupRequest groupRequest) {
        groupDAO.insertCommunity(groupRequest);
        // 2. 생성된 그룹 ID 가져오기
        int communityId = groupRequest.getCommunityId();

        // 3. 생성자를 멤버로 추가
        MemberRequest memberRequest = new MemberRequest(groupRequest.getUserKey(), communityId, "참가");
        groupDAO.insertMember(memberRequest);
    }

    @Override
    public Page<GroupListResponse> getCommunityList(int page, int size) {
        int offset = page * size;
        List<GroupListResponse> groups = groupDAO.getCommunityList(offset, size);
        int total = groupDAO.countCommunity();

        return new PageImpl<>(groups, PageRequest.of(page, size), total);
    }

    @Override
    public GroupDetailResponse getCommunityDetail(int groupId) {
        return groupDAO.getCommunityDetail(groupId);
    }

    @Override
    public List<MemberResponse> getGroupMembers(int groupId) {
        return groupDAO.getGroupMembers(groupId);
    }

    @Override
    public boolean isMemberAlreadyRequested(int userKey, int communityId) {
        return groupDAO.isMemberAlreadyRequested(userKey,communityId);
    }

    @Override
    @Transactional
    public void requestToJoin(int userKey, int communityId) {
        groupDAO.insertMember(new MemberRequest(userKey, communityId, "신청"));
    }
}

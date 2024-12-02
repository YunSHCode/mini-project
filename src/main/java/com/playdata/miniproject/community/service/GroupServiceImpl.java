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
    private final GroupFileService groupFileService;

    @Override
    @Transactional
    public void insertCommunity(GroupRequest groupRequest) {
        groupDAO.insertCommunity(groupRequest);
        // 2. 생성된 그룹 ID 가져오기
        int communityId = groupRequest.getCommunityId();

        // 3. 생성자를 멤버로 추가
        MemberRequest memberRequest = new MemberRequest(groupRequest.getUserKey(), communityId, "개설자");
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
    public List<MemberResponse> getPendingMembers(int groupId) {
        return groupDAO.getPendingMembers(groupId);
    }

    @Override
    public void approveMember(int communityId, int userKey) {
        // 현재 커뮤니티 정보 가져오기
        GroupDetailResponse group = groupDAO.getCommunityDetail(communityId);
        if (group.getCommunityMember() >= group.getCommunityMemberMax()) {
            throw new IllegalStateException("Maximum number of members exceeded. Cannot approve member.");
        }

        groupDAO.approveMember(communityId, userKey);
        groupDAO.increaseCommunityMemberCount(communityId);
    }

    @Override
    public void removeMember(int communityId, int userKey, boolean isExpelled) {
        groupDAO.removeMember(communityId, userKey);
        if (isExpelled) groupDAO.decreaseCommunityMemberCount(communityId);
    }

    @Override
    public Page<GroupListResponse> findMyGroups(int userKey, String memberStatus, int page, int size) {
        int offset = page * size;
        List<GroupListResponse> groups = groupDAO.findMyGroups(userKey, memberStatus,offset, size);
        int total = groupDAO.countMyGroups(userKey, memberStatus);
        return new PageImpl<>(groups, PageRequest.of(page, size), total);
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

    @Override
    public Page<GroupListResponse> findMyCreatedGroups(int userKey, int page, int size) {
        int offset = page * size;
        List<GroupListResponse> groups = groupDAO.findMyCreatedGroups(userKey, offset, size);
        int total = groupDAO.countMyCreatedGroups(userKey);
        return new PageImpl<>(groups, PageRequest.of(page, size), total);
    }

    @Override
    @Transactional
    public void updateCommunity(GroupRequest groupRequest, String oldFileName) {
        groupDAO.updateCommunity(groupRequest);
        // 기존 파일 삭제 처리
        if (oldFileName != null && !"NO_CHANGE".equals(groupRequest.getCommunityPictureGenerated())) {
            groupFileService.deleteGroupFile(oldFileName);
        }
    }

    @Override
    public void deleteCommunity(int id) {
        GroupDetailResponse existingCommunity = groupDAO.getCommunityDetail(id);
        if (existingCommunity != null && existingCommunity.getCommunityPictureGenerated() != null) {
            groupFileService.deleteGroupFile(existingCommunity.getCommunityPictureGenerated());
        }
        groupDAO.deleteCommunity(id);
    }

}

package com.playdata.miniproject.community.dao;

import com.playdata.miniproject.community.dto.*;
import com.playdata.miniproject.community.mapper.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupDAOImpl implements GroupDAO {
    private final GroupMapper mapper;

    @Override
    public void insertCommunity(GroupRequest groupRequest) {
        mapper.insertGroup(groupRequest);
    }

    @Override
    public List<GroupListResponse> getCommunityList(int offset, int size) {
        return mapper.getCommunityList(offset, size);
    }

    @Override
    public int countCommunity() {
        return mapper.countCommunity();
    }

    @Override
    public GroupDetailResponse getCommunityDetail(int groupId) {
        return mapper.getCommunityDetail(groupId);
    }

    @Override
    public List<MemberResponse> getGroupMembers(int groupId) {
        return mapper.getGroupMembers(groupId);
    }

    @Override
    public void insertMember(MemberRequest member) {
        mapper.insertMember(member);
    }

    @Override
    public boolean isMemberAlreadyRequested(int userKey, int communityId) {
        return mapper.isMemberAlreadyRequested(userKey, communityId) > 0;
    }

    @Override
    public List<GroupListResponse> findMyCreatedGroups(int userKey, int offset, int size) {
        return mapper.findMyCreatedGroups(userKey, offset, size);
    }

    @Override
    public int countMyCreatedGroups(int userKey) {
        return mapper.countMyCreatedGroups(userKey);
    }

    @Override
    public void updateCommunity(GroupRequest groupRequest) {
        mapper.updateCommunity(groupRequest);
    }

    @Override
    public void deleteCommunity(int id) {
        mapper.deleteCommunity(id);
    }
}

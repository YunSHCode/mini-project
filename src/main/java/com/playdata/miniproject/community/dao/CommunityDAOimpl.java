package com.playdata.miniproject.community.dao;

import com.playdata.miniproject.community.dto.CommunityDTO;
import com.playdata.miniproject.community.mapper.CommunityMapper;
import com.playdata.miniproject.community.service.CommunitySevice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommunityDAOimpl implements CommunityDAO {
    private final CommunityMapper mapper;
    private final CommunityMapper communityMapper;

    @Override
    public int registerCommunity(CommunityDTO community){
        System.out.println("========dao==============");
        System.out.println(community);
        return communityMapper.insert(community);
    }
    @Override
    public List<CommunityDTO> getCommunityList() {
        List<CommunityDTO> list =  mapper.getCommunityList();
        System.out.println("++++++++++dao+++++++");
        System.out.println(list);
        return list;
    }

    @Override
    public CommunityDTO getCommunityById(int id) {
        System.out.println("++++++++++상세페이지목록+++++++");
        return mapper.getCommunityById(id);


    }

    @Override
    public int deleteCommunity(int id) {
        return 0;
    }

    @Override
    public int updateCommunity(CommunityDTO community) {
        return 0;
    }


}

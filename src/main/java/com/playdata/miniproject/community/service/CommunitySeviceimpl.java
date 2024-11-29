package com.playdata.miniproject.community.service;


import com.playdata.miniproject.community.dao.CommunityDAO;
import com.playdata.miniproject.community.dto.CommunityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CommunitySeviceimpl implements CommunitySevice {
    private final CommunityDAO communityDAO;
    @Override
    public List<CommunityDTO> getCommunityList() {
        System.out.println("상세페이지");
        return communityDAO.getCommunityList();
    }
    @Override
    public CommunityDTO getCommunityById(int id) {
        System.out.println("dsadsa");
        return communityDAO.getCommunityById(id);
    }
}


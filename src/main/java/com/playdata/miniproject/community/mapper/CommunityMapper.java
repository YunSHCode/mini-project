package com.playdata.miniproject.community.mapper;

import com.playdata.miniproject.community.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommunityMapper {
    int insert(CommunityDTO community);
    List<CommunityDTO> getCommunityList();
    CommunityDTO getCommunityById(int id);
    int delete(int id);
    int update(CommunityDTO community);

}



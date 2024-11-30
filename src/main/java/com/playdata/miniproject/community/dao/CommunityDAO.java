package com.playdata.miniproject.community.dao;

import com.playdata.miniproject.community.dto.CommunityDTO; // CommunityDTO 클래스
import java.util.List; // List 사용을 위한 import

public interface CommunityDAO {
  //
  int registerCommunity(CommunityDTO community);//(등록)
  List<CommunityDTO> getCommunityList();
  CommunityDTO getCommunityById(int communityId);       //상세페이지(read)
  int updateCommunity(CommunityDTO community);
  int deleteCommunity(int communityId);
}

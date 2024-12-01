
package com.playdata.miniproject.community.service;


import com.playdata.miniproject.community.dto.CommunityDTO;

import java.util.List;


public interface CommunitySevice {
    List<CommunityDTO> getCommunityList();

    CommunityDTO getCommunityById(int id);
}

package com.playdata.miniproject.cafe.service;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;

import java.util.List;

public interface CafeServiceImpl {

    List<CafeDTO> selectmap();
    List<MenuDTO> selectmenu();
}

package com.playdata.miniproject.cafe.service;

import com.playdata.miniproject.cafe.dao.MapDAO;
import com.playdata.miniproject.cafe.dao.MenuDAO;
import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService implements CafeServiceImpl{

    private final MapDAO mapDAO;
    private final MenuDAO menuDAO;

    @Override
    public List<CafeDTO> selectmap() {
     List<CafeDTO> cafelist = mapDAO.selectmap();
        System.out.println("cafelist = " + cafelist);
        return cafelist;
    }

    @Override
    public List<MenuDTO> selectmenu() {
        List<MenuDTO> selectMenduList = menuDAO.selectMenuDU();
        return selectMenduList;
    }
}

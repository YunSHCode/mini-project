package com.playdata.miniproject.cafe.dao;

import com.playdata.miniproject.cafe.dto.MenuDTO;
import com.playdata.miniproject.cafe.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuImpl implements MenuDAO {
    private final MapMapper menuMapper;

    @Override
    public List<MenuDTO> selectMenuDU() {
        List<MenuDTO> selectMenuDU = menuMapper.selectMenuDU();
        System.out.println("selectMenuDU = " + selectMenuDU);
        return selectMenuDU;
    }
}

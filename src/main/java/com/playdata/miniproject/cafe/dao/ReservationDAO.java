package com.playdata.miniproject.cafe.dao;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;

import java.util.List;

public interface ReservationDAO {
    CafeDTO selectById(int cafeId);

    List<MenuDTO> menuListByCafe(int cafeId);
}

package com.playdata.miniproject.cafe.service;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;

import java.util.List;

public interface ReservationService {
    CafeDTO selectById(int cafeId);

    List<MenuDTO> menuListByCafe(int cafeId);
}

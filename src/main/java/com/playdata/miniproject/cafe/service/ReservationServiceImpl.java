package com.playdata.miniproject.cafe.service;

import com.playdata.miniproject.cafe.dao.ReservationDAO;
import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationDAO reservationDAO;
    @Override
    public CafeDTO selectById(int cafeId) {
        return reservationDAO.selectById(cafeId);
    }

    @Override
    public List<MenuDTO> menuListByCafe(int cafeId) {
        return reservationDAO.menuListByCafe(cafeId);
    }
}

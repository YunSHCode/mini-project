package com.playdata.miniproject.cafe.dao;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;
import com.playdata.miniproject.cafe.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationDAOImpl implements ReservationDAO{
    private final ReservationMapper mapper;

    @Override
    public CafeDTO selectById(int cafeId) {
        return mapper.cafeById(cafeId);
    }

    @Override
    public List<MenuDTO> menuListByCafe(int cafeId) {
        return mapper.menuListByCafe(cafeId);
    }
}
